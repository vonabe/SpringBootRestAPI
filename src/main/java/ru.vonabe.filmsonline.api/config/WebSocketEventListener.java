package ru.vonabe.filmsonline.api.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.messaging.support.GenericMessage;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionConnectedEvent;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

import java.util.LinkedList;
import java.util.Map;

@Component
public class WebSocketEventListener {

    private static final Logger logger = LoggerFactory.getLogger(WebSocketEventListener.class);

    @Autowired private SimpMessageSendingOperations messagingTemplate;

    @EventListener
    public void handleWebSocketConnectListener(SessionConnectedEvent event) {
        GenericMessage message = (GenericMessage) event.getMessage().getHeaders().get("simpConnectMessage");
        Map<String, LinkedList<String>> nativeHeaders = (Map<String, LinkedList<String>>)message.getHeaders().get("nativeHeaders");
        String token = nativeHeaders.get("token").get(0);
        Map<String, Object> attribute = (Map<String, Object>) message.getHeaders().get("simpSessionAttributes");
        attribute.put("token", token);
//        StompHeaderAccessor headerAccessor = StompHeaderAccessor.wrap(event.getMessage());
        logger.info("Received a new web socket connection ");
    }

    @EventListener
    public void handleWebSocketDisconnectListener(SessionDisconnectEvent event) {
        StompHeaderAccessor headerAccessor = StompHeaderAccessor.wrap(event.getMessage());
        System.out.println("disconnect");
//        System.out.println("User disconnect "
//                +headerAccessor.getSessionAttributes().get("userid") + ", "
//                +Arrays.toString(((List<String>) headerAccessor.getSessionAttributes().get("subscribers")).toArray())
//        );

//        messagingTemplate.convertAndSend("", );

//        String username = (String) headerAccessor.getSessionAttributes().get("username");
//        if(username != null) {
//            logger.info("User Disconnected : " + username);
//
//            MessageEntity chatMessage = new MessageEntity();
//            chatMessage.setUsername(username);
////            chatMessage.setType(ChatMessage.MessageType.LEAVE);
////            chatMessage.setSender(username);
//
//            messagingTemplate.convertAndSend("/topic/public", chatMessage);
//        }
    }

}
