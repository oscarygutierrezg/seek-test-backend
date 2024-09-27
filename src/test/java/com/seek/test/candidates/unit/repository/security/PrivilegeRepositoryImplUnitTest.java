package com.seek.test.candidates.unit.repository.security;

import com.seek.test.candidates.domain.model.security.Privilege;
import com.seek.test.candidates.infrastracture.repository.security.PrivilegeJpaRepository;
import com.seek.test.candidates.infrastracture.repository.security.PrivilegeRepositoryImpl;
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
class PrivilegeRepositoryImplUnitTest {

    @Mock
    private PrivilegeJpaRepository privilegeJpaRepository;

    @InjectMocks
    private PrivilegeRepositoryImpl privilegeRepository;

    @Test
    void whenFindByName_thenReturnPrivilege() {
        // Given
        String privilegeName = "READ_PRIVILEGE";
        Privilege mockPrivilege = new Privilege(privilegeName);

        when(privilegeJpaRepository.findByName(privilegeName)).thenReturn(mockPrivilege);

        // When
        Privilege foundPrivilege = privilegeRepository.findByName(privilegeName);

        // Then
        assertNotNull(foundPrivilege);
        assertEquals(privilegeName, foundPrivilege.getName());
        verify(privilegeJpaRepository).findByName(privilegeName);
    }

    @Test
    void whenSavePrivilege_thenPrivilegeIsSaved() {
        // Given
        Privilege mockPrivilege = new Privilege("WRITE_PRIVILEGE");

        // When
        when(privilegeJpaRepository.save(any(Privilege.class))).thenReturn(mockPrivilege);
        Privilege savedPrivilege = privilegeRepository.save(mockPrivilege);

        // Then
        assertNotNull(savedPrivilege);
        assertEquals("WRITE_PRIVILEGE", savedPrivilege.getName());
        verify(privilegeJpaRepository).save(mockPrivilege);
    }

    @Test
    void whenDeletePrivilege_thenPrivilegeIsDeleted() {
        // Given
        Privilege mockPrivilege = new Privilege("DELETE_PRIVILEGE");

        // When
        privilegeRepository.delete(mockPrivilege);

        // Then
        verify(privilegeJpaRepository).delete(mockPrivilege);
    }
}
