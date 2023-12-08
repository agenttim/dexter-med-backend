package ru.dextermed.dextermedbackend.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.dextermed.dextermedbackend.security.UserEntityDetails;

@RestController
@RequestMapping("/api")
public class SecurityController {
    @GetMapping("/showUserInfo")
    public void showUserInfo() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserEntityDetails userEntityDetails = (UserEntityDetails) authentication.getPrincipal();
        System.out.println(userEntityDetails.getUserEntity());
    }

}