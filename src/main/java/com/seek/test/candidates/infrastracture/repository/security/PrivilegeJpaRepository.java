package com.seek.test.candidates.infrastracture.repository.security;

import com.seek.test.candidates.domain.model.security.Privilege;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PrivilegeJpaRepository extends JpaRepository<Privilege, UUID> {

    Privilege findByName(String name);

    @Override
    void delete(Privilege privilege);

}
