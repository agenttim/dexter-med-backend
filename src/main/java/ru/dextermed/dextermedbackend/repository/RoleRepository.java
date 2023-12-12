package ru.dextermed.dextermedbackend.repository;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import ru.dextermed.dextermedbackend.entities.Role;

import java.util.Optional;

public interface RoleRepository extends CrudRepository<Role, Long> {

    @Query("SELECT * FROM app_role WHERE name = :name") // Обрати внимание на изменение здесь
    Optional<Role> findByName(@Param("name") String name);
}
