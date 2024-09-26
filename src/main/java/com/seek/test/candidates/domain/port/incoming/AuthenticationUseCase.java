package com.seek.test.candidates.domain.port.incoming;

import org.springframework.security.core.Authentication;

public interface AuthenticationUseCase {
    Authentication getAuthentication();
}
