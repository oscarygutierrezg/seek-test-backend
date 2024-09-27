package com.seek.test.candidates.unit.application.service;

import com.seek.test.candidates.application.dto.CandidateDto;
import com.seek.test.candidates.application.dto.CandidateMapper;
import com.seek.test.candidates.application.dto.CandidateRequest;
import com.seek.test.candidates.application.dto.CandidateUpdateRequest;
import com.seek.test.candidates.application.service.CandidateService;
import com.seek.test.candidates.domain.model.Candidate;
import com.seek.test.candidates.domain.port.outgoing.CandidateRepositoryPort;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CandidateServiceUnitTest  {
    @Mock
    private CandidateRepositoryPort candidateRepositoryPort;

    @Mock
    private CandidateMapper candidateMapper;

    @InjectMocks
    private CandidateService candidateService;

    private UUID candidateId;
    private Candidate candidate;
    private CandidateDto candidateDto;

    @BeforeEach
    void setUp() {
        candidateId = UUID.randomUUID();
        candidate = new Candidate();
        candidateDto = new CandidateDto();
    }

    @Test
    void whenFindAll_thenReturnListOfCandidateDtos() {
        // Given
        List<Candidate> mockCandidates = List.of(new Candidate(), new Candidate());
        List<CandidateDto> mockCandidateDtos = List.of(new CandidateDto(), new CandidateDto());

        when(candidateRepositoryPort.findAll()).thenReturn(mockCandidates);
        when(candidateMapper.toDto(any(Candidate.class))).thenReturn(new CandidateDto());

        // When
        List<CandidateDto> foundDtos = candidateService.findAll();

        // Then
        assertNotNull(foundDtos);
        assertEquals(2, foundDtos.size());
        verify(candidateRepositoryPort).findAll();
        verify(candidateMapper, times(2)).toDto(any(Candidate.class));
    }

    @Test
    void whenFindById_thenReturnCandidateDto() {
        // Given
        when(candidateRepositoryPort.findById(candidateId)).thenReturn(Optional.of(candidate));
        when(candidateMapper.toDto(candidate)).thenReturn(candidateDto);

        // When
        CandidateDto foundDto = candidateService.findById(candidateId);

        // Then
        assertNotNull(foundDto);
        assertEquals(candidateDto, foundDto);
        verify(candidateRepositoryPort).findById(candidateId);
        verify(candidateMapper).toDto(candidate);
    }

    @Test
    void whenCreateCandidate_thenReturnSavedCandidateDto() {
        // Given
        CandidateRequest candidateRequest = new CandidateRequest();
        when(candidateMapper.toModel(candidateRequest)).thenReturn(candidate);
        when(candidateRepositoryPort.save(candidate)).thenReturn(candidate);
        when(candidateMapper.toDto(candidate)).thenReturn(candidateDto);

        // When
        CandidateDto savedDto = candidateService.create(candidateRequest);

        // Then
        assertNotNull(savedDto);
        assertEquals(candidateDto, savedDto);
        verify(candidateMapper).toModel(candidateRequest);
        verify(candidateRepositoryPort).save(candidate);
        verify(candidateMapper).toDto(candidate);
    }

    @Test
    void whenUpdateCandidate_thenReturnUpdatedCandidateDto() {
        // Given
        CandidateUpdateRequest updateRequest = new CandidateUpdateRequest();
        when(candidateRepositoryPort.findById(candidateId)).thenReturn(Optional.of(candidate));
        doNothing().when(candidateMapper).updateModel(candidate, updateRequest);
        when(candidateRepositoryPort.save(candidate)).thenReturn(candidate);
        when(candidateMapper.toDto(candidate)).thenReturn(candidateDto);

        // When
        CandidateDto updatedDto = candidateService.update(candidateId, updateRequest);

        // Then
        assertNotNull(updatedDto);
        assertEquals(candidateDto, updatedDto);
        verify(candidateRepositoryPort).findById(candidateId);
        verify(candidateMapper).updateModel(candidate, updateRequest);
        verify(candidateRepositoryPort).save(candidate);
        verify(candidateMapper).toDto(candidate);
    }

    @Test
    void whenDeleteCandidate_thenVerifyDeletion() {
        // Given
        when(candidateRepositoryPort.findById(candidateId)).thenReturn(Optional.of(candidate));

        // When
        candidateService.delete(candidateId);

        // Then
        verify(candidateRepositoryPort).findById(candidateId);
        verify(candidateRepositoryPort).delete(candidate);
    }

    @Test
    void whenFindByIdAndCandidateNotFound_thenThrowEntityNotFoundException() {
        // Given
        when(candidateRepositoryPort.findById(candidateId)).thenReturn(Optional.empty());

        // When
        assertThrows(EntityNotFoundException.class, () -> candidateService.findById(candidateId));

        // Then
        verify(candidateRepositoryPort).findById(candidateId);
    }
}