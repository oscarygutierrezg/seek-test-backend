package com.seek.test.candidates.infrastracture.repository;


import com.seek.test.candidates.domain.model.Candidate;
import com.seek.test.candidates.domain.port.outgoing.CandidateRepositoryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class CandidateRepositoryImpl implements CandidateRepositoryPort {

    private final CandidateJpaRepository candidateJpaRepository;

    @Override
    public List<Candidate> findAll() {
        return candidateJpaRepository.findAll();
    }

    @Override
    public Optional<Candidate> findById(UUID id) {
        return candidateJpaRepository.findById(id);
    }

    @Override
    public Optional<Candidate> findByEmail(String email) {
        return candidateJpaRepository.findByEmail(email);
    }

    @Override
    public Candidate save(Candidate candidate) {
        return candidateJpaRepository.save(candidate);
    }

    @Override
    public void delete(Candidate candidate) {
        candidateJpaRepository.delete(candidate);
    }

}