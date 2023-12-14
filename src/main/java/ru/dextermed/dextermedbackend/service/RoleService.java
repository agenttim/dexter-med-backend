package ru.dextermed.dextermedbackend.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.dextermed.dextermedbackend.entities.Role;
import ru.dextermed.dextermedbackend.repository.RoleRepository;

@Service
@RequiredArgsConstructor
public class RoleService {
    private final RoleRepository roleRepository;

    public Role getUserRole() {
        return roleRepository.findByName("ROLE_PATIENT").get();
    }
}