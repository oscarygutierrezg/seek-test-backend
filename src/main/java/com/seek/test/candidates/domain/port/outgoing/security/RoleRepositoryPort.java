package com.seek.test.candidates.domain.port.outgoing.security;

import com.seek.test.candidates.domain.model.security.Role;

public interface RoleRepositoryPort {

    Role findByName(String name);

    void delete(Role role);

    Role save(Role role);
}
