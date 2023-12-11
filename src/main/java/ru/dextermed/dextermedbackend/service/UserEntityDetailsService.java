package ru.dextermed.dextermedbackend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.dextermed.dextermedbackend.model.UserEntity;
import ru.dextermed.dextermedbackend.repository.UserEntityRepository;
import ru.dextermed.dextermedbackend.security.UserEntityDetails;

import java.util.Optional;

@Service
public class UserEntityDetailsService implements UserDetailsService {

    private final UserEntityRepository userEntityRepository;

    @Autowired
    public UserEntityDetailsService(UserEntityRepository userEntityRepository) {
        this.userEntityRepository = userEntityRepository;
    }

    public Optional<UserEntity> findByUserName(String username) {
        return userEntityRepository.findByUsername(username);
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UserEntity> userEntity = userEntityRepository.findByUsername(username);

        if (userEntity.isEmpty())
            throw new UsernameNotFoundException("User not found!");

        return new UserEntityDetails(userEntity.get());
    }


}
