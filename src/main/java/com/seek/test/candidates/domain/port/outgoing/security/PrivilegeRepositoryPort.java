package com.seek.test.candidates.domain.port.outgoing.security;

import com.seek.test.candidates.domain.model.security.Privilege;

public interface PrivilegeRepositoryPort{

    Privilege findByName(String name);

    void delete(Privilege privilege);

    Privilege save(Privilege privilege);
}
