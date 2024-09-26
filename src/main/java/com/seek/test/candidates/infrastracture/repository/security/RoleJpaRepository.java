package com.seek.test.candidates.infrastracture.repository.security;

import com.seek.test.candidates.domain.model.security.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface RoleJpaRepository extends JpaRepository<Role, UUID> {

    Role findByName(String name);

    @Override
    void delete(Role role);

}
