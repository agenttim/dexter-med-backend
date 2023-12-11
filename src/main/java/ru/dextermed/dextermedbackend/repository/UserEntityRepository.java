package ru.dextermed.dextermedbackend.repository;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.repository.CrudRepository;
import ru.dextermed.dextermedbackend.model.Role;
import ru.dextermed.dextermedbackend.model.UserEntity;

import java.util.List;
import java.util.Optional;

public interface UserEntityRepository extends CrudRepository<UserEntity, Long> {

    @Query("SELECT * FROM app_user WHERE username = :username")
    Optional<UserEntity> findByUsername(@Param("username") String username);

    @Query("SELECT r.id, r.name FROM app_role r " +
            "JOIN user_role ur ON r.id = ur.role_id " +
            "WHERE ur.user_id = :userId")
    List<Role> findRolesByUserId(@Param("userId") Long userId);

}
