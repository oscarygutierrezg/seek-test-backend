package com.seek.test.candidates.infrastructure.adapter;


import com.seek.test.candidates.application.dto.CandidateDto;
import com.seek.test.candidates.application.dto.CandidateRequest;
import com.seek.test.candidates.application.dto.CandidateUpdateRequest;
import com.seek.test.candidates.application.service.CandidateService;
import com.seek.test.candidates.domain.port.incoming.CandidateUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.UUID;

@RestController
@RequestMapping("/v1/candidates")
@RequiredArgsConstructor
public class CandidateController {

    private final CandidateUseCase candidateService;

    @GetMapping
    public List<CandidateDto> getAllCandidates() {
        return candidateService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<CandidateDto> getCandidateById(@PathVariable UUID id) {
        CandidateDto candidate = candidateService.findById(id);
        return ResponseEntity.ok(candidate);
    }

    @PostMapping
    public ResponseEntity<CandidateDto> createCandidate(@RequestBody CandidateRequest candidate) {
        CandidateDto newCandidate = candidateService.create(candidate);
        return ResponseEntity.ok(newCandidate);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CandidateDto> updateCandidate(
            @PathVariable UUID id,
            @RequestBody CandidateUpdateRequest candidateDetails) {
        CandidateDto updatedCandidate = candidateService.update(id, candidateDetails);
        return ResponseEntity.ok(updatedCandidate);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Boolean>> deleteCandidate(@PathVariable UUID id) {
        candidateService.delete(id);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }
}
