package ru.dextermed.dextermedbackend.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import ru.dextermed.dextermedbackend.service.UserEntityDetailsService;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final UserEntityDetailsService userEntityDetailsService;

    @Autowired
    public SecurityConfig(UserEntityDetailsService userEntityDetailsService) {
        this.userEntityDetailsService = userEntityDetailsService;
    }

    @Autowired
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userEntityDetailsService);
    }

    @Bean
    public PasswordEncoder getPasswordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }
}
