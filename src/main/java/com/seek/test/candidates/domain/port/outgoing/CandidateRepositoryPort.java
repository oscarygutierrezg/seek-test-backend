package com.seek.test.candidates.domain.port.outgoing;


import com.seek.test.candidates.domain.model.Candidate;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CandidateRepositoryPort {
    List<Candidate> findAll();
    Optional<Candidate> findById(UUID id);
    Optional<Candidate> findByEmail(String email);
    Candidate save(Candidate candidate);
    void delete(Candidate candidate);
}
