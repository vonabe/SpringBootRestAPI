package ru.vonabe.filmsonline.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.vonabe.filmsonline.api.entites.api.TokenEntity;

@Repository
@Transactional("db1TransactionManager")
public interface TokenRepository extends JpaRepository<TokenEntity, String> {
    TokenEntity findByToken(String token);
}
