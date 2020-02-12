package ru.vonabe.filmsonline.api.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.vonabe.filmsonline.api.entites.api.TokenEntity;
import ru.vonabe.filmsonline.api.repository.TokenRepository;

@Service
@Transactional("db1TransactionManager")
public class TokenServices {

    @Autowired private TokenRepository tokenRepository;

    public TokenEntity findByToken(String token){
        return tokenRepository.findByToken(token);
    }

    public void save(TokenEntity tokenEntity){
        tokenRepository.save(tokenEntity);
    }

}
