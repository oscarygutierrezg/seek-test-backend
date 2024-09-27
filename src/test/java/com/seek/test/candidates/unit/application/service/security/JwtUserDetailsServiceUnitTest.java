package com.seek.test.candidates.unit.application.service.security;

import com.seek.test.candidates.application.service.security.JwtUserDetailsService;
import com.seek.test.candidates.domain.model.security.Privilege;
import com.seek.test.candidates.domain.model.security.Role;
import com.seek.test.candidates.domain.port.outgoing.security.UserRepositoryPort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class JwtUserDetailsServiceUnitTest {
    @Mock
    private UserRepositoryPort userRepository;

    @InjectMocks
    private JwtUserDetailsService jwtUserDetailsService;

    private com.seek.test.candidates.domain.model.security.User mockUser;
    private Role role;
    private Privilege privilege;

    @BeforeEach
    void setUp() {

        mockUser = new com.seek.test.candidates.domain.model.security.User();
        mockUser.setEmail("test@example.com");
        mockUser.setPassword("password");

        role = new Role();
        role.setName("ROLE_USER");

        privilege = new Privilege();
        privilege.setName("READ_PRIVILEGE");

        List<Role> roles = new ArrayList<>();
        List<Privilege> privileges = new ArrayList<>();

        privileges.add(privilege);
        role.setPrivileges(privileges);
        roles.add(role);

        mockUser.setRoles(roles);
    }

    @Test
    void whenLoadUserByUsername_thenReturnUserDetails() {
        // Given
        when(userRepository.findByEmail("test@example.com")).thenReturn(Optional.of(mockUser));

        // When
        User userDetails = (User) jwtUserDetailsService.loadUserByUsername("test@example.com");

        // Then
        assertNotNull(userDetails);
        assertEquals(mockUser.getEmail(), userDetails.getUsername());
        assertEquals(mockUser.getPassword(), userDetails.getPassword());

        // Then
        verify(userRepository).findByEmail("test@example.com");
    }

    @Test
    void whenLoadUserByUsernameAndUserNotFound_thenThrowUsernameNotFoundException() {
        // Given
        when(userRepository.findByEmail("nonexistent@example.com")).thenReturn(Optional.empty());

        // When
        assertThrows(UsernameNotFoundException.class, () -> jwtUserDetailsService.loadUserByUsername("nonexistent@example.com"));

        // Then
        verify(userRepository).findByEmail("nonexistent@example.com");
    }

}
