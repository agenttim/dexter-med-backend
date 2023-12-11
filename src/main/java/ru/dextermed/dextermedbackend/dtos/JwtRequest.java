package ru.dextermed.dextermedbackend.dtos;

import lombok.Data;

@Data
public class JwtRequest {
    private String username;
    private String password;
}
