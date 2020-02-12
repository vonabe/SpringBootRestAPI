package ru.vonabe.filmsonline.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import ru.vonabe.filmsonline.api.entites.api.chat.MessageEntity;

import java.util.List;

@Repository
@Transactional("db1TransactionManager")
public interface MessageRepository extends JpaRepository<MessageEntity, Long> {

    List<MessageEntity> findAllByChannelid(@PathVariable Long channelid);

}
