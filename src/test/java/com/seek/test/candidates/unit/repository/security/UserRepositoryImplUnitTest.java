package com.seek.test.candidates.unit.repository.security;

import com.seek.test.candidates.domain.model.security.User;
import com.seek.test.candidates.infrastracture.repository.security.UserJpaRepository;
import com.seek.test.candidates.infrastracture.repository.security.UserRepositoryImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserRepositoryImplUnitTest {
    @Mock
    private UserJpaRepository userJpaRepository;

    @InjectMocks
    private UserRepositoryImpl userRepository;

    @Test
    void whenFindByEmail_thenReturnUser() {
        // Given
        String userEmail = "test@example.com";
        User mockUser = new User();
        mockUser.setEmail(userEmail);

        when(userJpaRepository.findByEmail(userEmail)).thenReturn(Optional.of(mockUser));

        // When
        Optional<User> foundUser = userRepository.findByEmail(userEmail);

        // Then
        assertNotNull(foundUser);
        assertEquals(userEmail, foundUser.get().getEmail());
        verify(userJpaRepository).findByEmail(userEmail);
    }

    @Test
    void whenSaveUser_thenUserIsSaved() {
        // Given
        User mockUser = new User();
        mockUser.setEmail("user@example.com");

        when(userJpaRepository.save(any(User.class))).thenReturn(mockUser);

        // When
        User savedUser = userRepository.save(mockUser);

        // Then
        assertNotNull(savedUser);
        assertEquals("user@example.com", savedUser.getEmail());
        verify(userJpaRepository).save(mockUser);
    }
}