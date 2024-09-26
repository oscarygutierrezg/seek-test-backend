package com.seek.test.candidates.domain.port.incoming;

import com.seek.test.candidates.application.dto.CandidateDto;
import com.seek.test.candidates.application.dto.CandidateRequest;
import com.seek.test.candidates.application.dto.CandidateUpdateRequest;

import java.util.List;
import java.util.UUID;

public interface CandidateUseCase {
    List<CandidateDto> findAll();
    CandidateDto findById(UUID id);
    CandidateDto create(CandidateRequest candidate);
    CandidateDto update(UUID id, CandidateUpdateRequest updateCandidate);
    void delete(UUID id);
}