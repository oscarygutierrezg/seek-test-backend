package com.seek.test.candidates.application.dto;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Builder
public class CandidateDto {

	private UUID id;
	private String name;
	private String email;
	private String gender;
	private double expectedSalary;
	private int yearsExperience;
}
