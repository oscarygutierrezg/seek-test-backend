package com.seek.test.candidates.unit.repository.security;

import com.seek.test.candidates.domain.model.security.Role;
import com.seek.test.candidates.infrastracture.repository.security.RoleJpaRepository;
import com.seek.test.candidates.infrastracture.repository.security.RoleRepositoryImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class RoleRepositoryImplUnitTest {

    @Mock
    private RoleJpaRepository roleJpaRepository;

    @InjectMocks
    private RoleRepositoryImpl roleRepository;

    @Test
    void whenFindByName_thenReturnRole() {
        // Given
        String roleName = "ADMIN";
        Role mockRole = new Role(roleName);

        when(roleJpaRepository.findByName(roleName)).thenReturn(mockRole);

        // When
        Role foundRole = roleRepository.findByName(roleName);

        // Then
        assertNotNull(foundRole);
        assertEquals(roleName, foundRole.getName());
        verify(roleJpaRepository).findByName(roleName);
    }

    @Test
    void whenSaveRole_thenRoleIsSaved() {
        // Given
        Role mockRole = new Role("USER");
        when(roleJpaRepository.save(any(Role.class))).thenReturn(mockRole);

        // When
        Role savedRole = roleRepository.save(mockRole);

        // Then
        assertNotNull(savedRole);
        assertEquals("USER", savedRole.getName());
        verify(roleJpaRepository).save(mockRole);
    }

    @Test
    void whenDeleteRole_thenRoleIsDeleted() {
        // Given
        Role mockRole = new Role("GUEST");

        // When
        roleRepository.delete(mockRole);

        // Then
        verify(roleJpaRepository).delete(mockRole);
    }
}