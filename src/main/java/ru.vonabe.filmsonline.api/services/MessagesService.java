package ru.vonabe.filmsonline.api.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.vonabe.filmsonline.api.entites.api.chat.MessageEntity;
import ru.vonabe.filmsonline.api.repository.MessageRepository;

import java.util.List;

@Service
@Transactional("db1TransactionManager")
public class MessagesService {

    @Autowired private MessageRepository messageRepository;

    public List<MessageEntity> findAllByChannelId(Long channelid){
        return messageRepository.findAllByChannelid(channelid);
    }

    public void save(MessageEntity messageEntity){
        messageRepository.save(messageEntity);
    }

}
