package com.seek.test.candidates.unit.repository;

import com.seek.test.candidates.domain.model.Candidate;
import com.seek.test.candidates.infrastracture.repository.CandidateJpaRepository;
import com.seek.test.candidates.infrastracture.repository.CandidateRepositoryImpl;
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
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CandidateRepositoryImplUnitTest  {

    @Mock
    private CandidateJpaRepository candidateJpaRepository;

    @InjectMocks
    private CandidateRepositoryImpl candidateRepository;

    @Test
    void whenFindAll_thenReturnListOfCandidates() {
        // Given
        Candidate candidate1 = new Candidate();
        candidate1.setEmail("candidate1@example.com");
        Candidate candidate2 = new Candidate();
        candidate2.setEmail("candidate2@example.com");
        List<Candidate> mockCandidates = List.of(candidate1, candidate2);

        when(candidateJpaRepository.findAll()).thenReturn(mockCandidates);

        // When
        List<Candidate> foundCandidates = candidateRepository.findAll();

        // Then
        assertNotNull(foundCandidates);
        assertEquals(2, foundCandidates.size());
        assertEquals("candidate1@example.com", foundCandidates.get(0).getEmail());
         verify(candidateJpaRepository).findAll();
    }

    @Test
    void whenFindById_thenReturnCandidate() {
        // Given
        UUID candidateId = UUID.randomUUID();
        Candidate mockCandidate = new Candidate();
        mockCandidate.setId(candidateId);

        when(candidateJpaRepository.findById(candidateId)).thenReturn(Optional.of(mockCandidate));

        // When
        Optional<Candidate> foundCandidate = candidateRepository.findById(candidateId);

        // Then
        assertNotNull(foundCandidate);
        assertEquals(candidateId, foundCandidate.get().getId());
        verify(candidateJpaRepository).findById(candidateId);
    }

    @Test
    void whenFindByEmail_thenReturnCandidate() {
        // Given
        String candidateEmail = "test@example.com";
        Candidate mockCandidate = new Candidate();
        mockCandidate.setEmail(candidateEmail);

        when(candidateJpaRepository.findByEmail(candidateEmail)).thenReturn(Optional.of(mockCandidate));

        // When
        Optional<Candidate> foundCandidate = candidateRepository.findByEmail(candidateEmail);

        // Then
        assertNotNull(foundCandidate);
        assertEquals(candidateEmail, foundCandidate.get().getEmail());
        verify(candidateJpaRepository).findByEmail(candidateEmail);
    }

    @Test
    void whenSaveCandidate_thenCandidateIsSaved() {
        // Given
        Candidate mockCandidate = new Candidate();
        mockCandidate.setEmail("candidate@example.com");

        when(candidateJpaRepository.save(any(Candidate.class))).thenReturn(mockCandidate);

        // When
        Candidate savedCandidate = candidateRepository.save(mockCandidate);

        // Then
        assertNotNull(savedCandidate);
        assertEquals("candidate@example.com", savedCandidate.getEmail());
        verify(candidateJpaRepository).save(mockCandidate);
    }

    @Test
    void whenDeleteCandidate_thenCandidateIsDeleted() {
        // Given
        Candidate mockCandidate = new Candidate();
        mockCandidate.setEmail("candidate@example.com");

        // When
        candidateRepository.delete(mockCandidate);

        // Then
        verify(candidateJpaRepository).delete(mockCandidate);
    }

}