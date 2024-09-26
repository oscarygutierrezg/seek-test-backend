package com.seek.test.candidates.application.service;

import com.seek.test.candidates.application.dto.CandidateDto;
import com.seek.test.candidates.application.dto.CandidateMapper;
import com.seek.test.candidates.application.dto.CandidateRequest;
import com.seek.test.candidates.application.dto.CandidateUpdateRequest;
import com.seek.test.candidates.domain.model.Candidate;
import com.seek.test.candidates.domain.port.incoming.CandidateUseCase;
import com.seek.test.candidates.domain.port.outgoing.CandidateRepositoryPort;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CandidateService implements CandidateUseCase {

    private final CandidateRepositoryPort candidateRepositoryPort;
    private final CandidateMapper candidateMapper;


    @Override
    public List<CandidateDto> findAll() {
        return candidateRepositoryPort.findAll().stream().map(candidateMapper::toDto).toList();
    }

    @Override
    public CandidateDto findById(UUID id) {
        return candidateMapper.toDto(findModelById(id));
    }

    @Override
    public CandidateDto create(CandidateRequest candidate) {
        return candidateMapper.toDto(candidateRepositoryPort.save(candidateMapper.toModel(candidate)));
    }

    @Override
    public CandidateDto update(UUID id, CandidateUpdateRequest updateCandidate) {
        Candidate candidate = findModelById(id);
        candidateMapper.updateModel(candidate, updateCandidate);
        candidateRepositoryPort.save(candidate);
        return candidateMapper.toDto(candidate);
    }

    @Override
    public void delete(UUID id) {
        Candidate candidate = findModelById(id);
        candidateRepositoryPort.delete(candidate);
    }

    private Candidate findModelById(UUID id) {
        return candidateRepositoryPort.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Candidate not found"));
    }
}