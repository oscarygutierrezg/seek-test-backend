package com.seek.test.candidates.infrastracture.repository.security;


import com.seek.test.candidates.domain.model.security.Privilege;
import com.seek.test.candidates.domain.port.outgoing.security.PrivilegeRepositoryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PrivilegeRepositoryImpl implements PrivilegeRepositoryPort {

    private final  PrivilegeJpaRepository privilegeJpaRepository;


    @Override
    public Privilege findByName(String name) {
        return privilegeJpaRepository.findByName(name);
    }

    @Override
    public void delete(Privilege privilege) {
        privilegeJpaRepository.delete(privilege);
    }

    @Override
    public Privilege save(Privilege privilege) {
        return privilegeJpaRepository.save(privilege);
    }
}