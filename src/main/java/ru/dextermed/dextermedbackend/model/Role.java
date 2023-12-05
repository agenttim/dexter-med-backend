package ru.dextermed.dextermedbackend.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Table("app_role")
public class Role {

    @Id
    private Long id;

    private String name;

    public Role() {
        // Пустой конструктор для работы с JPA
    }

    public Role(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    // Геттеры и сеттеры для всех полей

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
