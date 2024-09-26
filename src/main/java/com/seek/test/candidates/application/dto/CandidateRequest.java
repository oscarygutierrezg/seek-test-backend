package com.seek.test.candidates.application.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
public class CandidateRequest {
	
	protected String name;
	protected String email;
	protected String gender;
	protected double expectedSalary;
	protected int yearsExperience;
}
