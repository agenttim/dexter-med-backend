package ru.dextermed.dextermedbackend.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.dextermed.dextermedbackend.dtos.JwtRequest;
import ru.dextermed.dextermedbackend.dtos.RegistrationUserDto;
import ru.dextermed.dextermedbackend.service.AuthService;

import java.security.Principal;

@RestController
@RequestMapping("/api/profile")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping("/auth")
    public ResponseEntity<?> createAuthToken(@RequestBody JwtRequest authRequest) {
        return authService.createAuthToken(authRequest);
    }

    @PostMapping("/registration")
    public ResponseEntity<?> createNewUser(@RequestBody RegistrationUserDto registrationUserDto) {
        return authService.createNewUser(registrationUserDto);
    }

    @GetMapping("/user")
    public ResponseEntity<?> getUserProfile(Principal principal) {
        return ResponseEntity.ok(principal);
    }
}
