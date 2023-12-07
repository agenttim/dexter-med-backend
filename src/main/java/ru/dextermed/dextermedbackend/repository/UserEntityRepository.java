package ru.dextermed.dextermedbackend.repository;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.repository.CrudRepository;
import ru.dextermed.dextermedbackend.model.UserEntity;

import java.util.Optional;

public interface UserEntityRepository extends CrudRepository<UserEntity, Long> {

    @Query("SELECT * FROM app_user WHERE username = :username")
    Optional<UserEntity> findByUsername(@Param("username") String username);
}
