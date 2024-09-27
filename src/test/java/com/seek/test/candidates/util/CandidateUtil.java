package com.seek.test.candidates.util;

import com.github.javafaker.Faker;
import com.seek.test.candidates.application.dto.CandidateDto;
import com.seek.test.candidates.application.dto.CandidateRequest;
import com.seek.test.candidates.application.dto.CandidateUpdateRequest;
import com.seek.test.candidates.domain.port.incoming.CandidateUseCase;

public class CandidateUtil {

    private Faker faker = new Faker();
    
    public CandidateRequest createCandidateRequest() {
        CandidateRequest candidateRequest = new CandidateRequest();
        candidateRequest.setName(faker.name().name());
        candidateRequest.setEmail(faker.internet().emailAddress());
        candidateRequest.setGender("Male");
        candidateRequest.setExpectedSalary(60000);
        candidateRequest.setYearsExperience(5);
        return candidateRequest;
    }

    public CandidateUpdateRequest createCandidateUpdateRequest() {
        CandidateUpdateRequest updateRequest = new CandidateUpdateRequest();
        updateRequest.setName(faker.name().name());
        updateRequest.setEmail(faker.internet().emailAddress());
        updateRequest.setGender("Male");
        updateRequest.setExpectedSalary(70000);
        updateRequest.setYearsExperience(6);
        return updateRequest;
    }

    public CandidateDto createAndSaveCandidate(CandidateUseCase candidateService) {
        CandidateRequest request = createCandidateRequest();
        CandidateDto candidateDto = candidateService.create(request); // Asumiendo que el método create devuelve un CandidateDto
        return candidateDto;
    }
}
