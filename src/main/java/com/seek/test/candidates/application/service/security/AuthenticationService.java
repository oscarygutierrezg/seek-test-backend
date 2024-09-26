package com.seek.test.candidates.application.service.security;

import com.seek.test.candidates.domain.port.incoming.AuthenticationUseCase;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;


public class AuthenticationService implements AuthenticationUseCase {

    @Override
    public Authentication getAuthentication() {
        return SecurityContextHolder.getContext().getAuthentication();
    }
}
