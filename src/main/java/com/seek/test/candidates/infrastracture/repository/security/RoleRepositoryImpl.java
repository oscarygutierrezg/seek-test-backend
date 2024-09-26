package com.seek.test.candidates.infrastracture.repository.security;


import com.seek.test.candidates.domain.model.security.Role;
import com.seek.test.candidates.domain.port.outgoing.security.RoleRepositoryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RoleRepositoryImpl implements RoleRepositoryPort {

    private final RoleJpaRepository roleJpaRepository;

    @Override
    public Role findByName(String name) {
        return roleJpaRepository.findByName(name);
    }

    @Override
    public void delete(Role role) {
        roleJpaRepository.delete(role);
    }

    @Override
    public Role save(Role role) {
        return roleJpaRepository.save(role);
    }
}