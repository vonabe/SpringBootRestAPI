package ru.vonabe.filmsonline.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import ru.vonabe.filmsonline.api.entites.api.UserEntity;

@Repository
@Transactional("db1TransactionManager")
public interface UserRepository extends JpaRepository<UserEntity, String> {

    boolean existsByUsername(@PathVariable String username);

}
