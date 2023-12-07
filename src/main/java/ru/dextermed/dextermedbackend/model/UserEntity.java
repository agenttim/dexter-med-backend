package ru.dextermed.dextermedbackend.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.util.Set;

@Table("app_user")
public class UserEntity {

    @Id
    private Long id;

    private String username;

    private String password;

    //private Set<Role> roles;

    public UserEntity() {
        // Пустой конструктор для работы с JPA
    }

    public UserEntity(Long id, String username, String password, Set<Role> roles) {
        this.id = id;
        this.username = username;
        this.password = password;
        //this.roles = roles;
    }

    public UserEntity(String username, String password) {
        this.username = username;
        this.password = password;
    }

    // Геттеры и сеттеры для всех полей

    public Long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

/*    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }*/

    @Override
    public String toString() {
        return "UserEntity{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                /*", roles=" + roles +*/
                '}';
    }
}
