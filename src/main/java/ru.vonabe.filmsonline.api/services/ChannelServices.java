package ru.vonabe.filmsonline.api.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.vonabe.filmsonline.api.entites.api.chat.ChannelEntity;
import ru.vonabe.filmsonline.api.repository.ChannelRepository;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional("db1TransactionManager")
public class ChannelServices {

    @Autowired private ChannelRepository channelRepository;

    public ChannelEntity findById(Long id){
        return channelRepository.findById(id).get();
    }

    public ChannelEntity findByUserId(String userid){
        return channelRepository.findByUserid(userid);
    }

    public boolean isOnline(String userid){
        return channelRepository.findByUserid(userid).isOnline();
    }

    public boolean existChannelId(Long channelid){
        return channelRepository.existsById(channelid);
    }

    public List<ChannelEntity> findAllIsOnline(){
        return new ArrayList<>(channelRepository.findAllByIsOnline());
    }

    public List<ChannelEntity> findAllByIdKinopoisk(String idkinopois){
        return new ArrayList<>(channelRepository.findAllByIdkinopoisk(idkinopois));
    }

    public void save(ChannelEntity channelEntity){
        channelRepository.save(channelEntity);
    }

}
