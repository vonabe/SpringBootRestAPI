package ru.vonabe.filmsonline.api.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.vonabe.filmsonline.api.entites.api.UserEntity;
import ru.vonabe.filmsonline.api.repository.UserRepository;

@Service
@Transactional("db1TransactionManager")
public class UserServices {

    @Autowired private UserRepository userRepository;

    public UserEntity findById(String id){
        return userRepository.findById(id).orElse(null);
    }

    public boolean existsByUsername(String username){
        return userRepository.existsByUsername(username);
    }

    public void save(UserEntity entity){
        userRepository.save(entity);
    }

}
