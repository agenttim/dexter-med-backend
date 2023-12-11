package ru.dextermed.dextermedbackend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.dextermed.dextermedbackend.model.UserEntity;
import ru.dextermed.dextermedbackend.repository.RoleRepository;
import ru.dextermed.dextermedbackend.repository.UserEntityRepository;

import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserEntityDetailsService implements UserDetailsService {

    private final UserEntityRepository userEntityRepository;
    private final RoleRepository roleRepository;

    @Autowired
    public UserEntityDetailsService(UserEntityRepository userEntityRepository, RoleRepository roleRepository) {
        this.userEntityRepository = userEntityRepository;
        this.roleRepository = roleRepository;
    }

    public Optional<UserEntity> findByUserName(String username) {
        return userEntityRepository.findByUsername(username);
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity userEntity = findByUserName(username).orElseThrow(() -> new UsernameNotFoundException(
                String.format("Пользователь '%s' не найден", username)
        ));

        return new User(
                userEntity.getUsername(),
                userEntity.getPassword(),
                userEntity.getRoles().stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList())
        );
    }
}
