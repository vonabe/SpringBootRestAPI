package ru.vonabe.filmsonline.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import ru.vonabe.filmsonline.api.entites.api.chat.ChannelEntity;

import java.util.Collection;

@Repository
@Transactional("db1TransactionManager")
public interface ChannelRepository extends JpaRepository<ChannelEntity, Long> {

    ChannelEntity findByUserid(@PathVariable String userid);

    Collection<ChannelEntity> findAllByIdkinopoisk(@PathVariable String idkinopoisk);

    @Query(value = "FROM channels WHERE is_online=true", nativeQuery = true)
    Collection<ChannelEntity> findAllByIsOnline();

}
