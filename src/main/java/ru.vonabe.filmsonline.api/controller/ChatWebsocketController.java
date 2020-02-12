package ru.vonabe.filmsonline.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.annotation.SubscribeMapping;
import org.springframework.messaging.simp.user.SimpUserRegistry;
import org.springframework.stereotype.Controller;
import org.springframework.web.socket.messaging.SessionUnsubscribeEvent;
import ru.vonabe.filmsonline.api.entites.api.TokenEntity;
import ru.vonabe.filmsonline.api.entites.api.chat.MessageEntity;
import ru.vonabe.filmsonline.api.services.ChannelServices;
import ru.vonabe.filmsonline.api.services.MessagesService;
import ru.vonabe.filmsonline.api.services.TokenServices;
import ru.vonabe.filmsonline.api.services.UserServices;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

//@RequestMapping("/api/chat")
@Controller
public class ChatWebsocketController implements ApplicationListener<SessionUnsubscribeEvent> {

    @Autowired private TokenServices tokenServices;
    @Autowired private UserServices userServices;
    @Autowired private ChannelServices channelServices;
    @Autowired private MessagesService messagesService;
    @Autowired private SimpMessagingTemplate simpMessagingTemplate;
    @Autowired private SimpUserRegistry simpUserRegistry;

    @Override
    public void onApplicationEvent(SessionUnsubscribeEvent sessionUnsubscribeEvent) {
        Object[] objects = sessionUnsubscribeEvent.getMessage().getHeaders().entrySet().toArray();
        String token = ((Map<String, Object>) sessionUnsubscribeEvent.getMessage().getHeaders().get("simpSessionAttributes")).get("token").toString();
        System.out.println("Unsubscribe - "+ token);
    }

    @SubscribeMapping("/topic/{channel_id}")
    public List<MessageEntity> connectChannel(@DestinationVariable Long channel_id, SimpMessageHeaderAccessor accessor){
//        String token = accessor.getSessionAttributes().get("token").toString();
        System.out.println("subscribe");
        return messagesService.findAllByChannelId(channel_id);
    }

    @MessageMapping("/topic/{channel_id}")
    public void sendToChannel(@DestinationVariable Long channel_id, @Payload MessageEntity message, SimpMessageHeaderAccessor accessor) {
        try {
            System.out.println("Message: "+channel_id+", "+message.toString());
            if(accessor.getSessionAttributes().containsKey("token")) {
                String token = accessor.getSessionAttributes().get("token").toString();
                TokenEntity tokenEntity = tokenServices.findByToken(token);
                if (tokenEntity != null) {
                    if (tokenEntity.getUserid().equals(message.getUserid())) {
                        if (channelServices.existChannelId(channel_id)) {
                            message.setDate(LocalDateTime.now());
                            simpMessagingTemplate.convertAndSend("/topic/" + channel_id, message);
                            messagesService.save(message);
                        }
                    } else {
                        System.out.println("Токен не принадлежит юзеру token: " + token + ", TokenEntity: " + tokenEntity.toString() + ", bad user = " + message.toString());
                    }
                } else {
                    System.out.println("Пользователь отправляет сообщение c недействительным токеном");
                }
            } else {
                System.out.println("Пользователь отправляет сообщение не имея токена");
            }
        }catch (Exception e){e.printStackTrace();}
    }

    @MessageMapping("/hello")
    @SendTo("/topic/greetings")
    public MessageEntity greeting(@Payload String message) throws Exception {
        return new MessageEntity("Hello, " + message + "!");
    }

}
