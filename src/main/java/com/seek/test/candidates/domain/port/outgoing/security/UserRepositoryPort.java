package com.seek.test.candidates.domain.port.outgoing.security;

import com.seek.test.candidates.domain.model.security.User;

import java.util.Optional;

public interface UserRepositoryPort {

    Optional<User> findByEmail(String email);

    User save(User user);
}
