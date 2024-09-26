package com.seek.test.candidates.infrastracture.repository;


import com.seek.test.candidates.domain.model.Candidate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface CandidateJpaRepository extends JpaRepository<Candidate, UUID> {

    Optional<Candidate> findByEmail(String email);

}