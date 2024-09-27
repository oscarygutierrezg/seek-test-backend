package com.seek.test.candidates.application.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@Builder
public class CandidateDto {

	private UUID id;
	private String name;
	private String email;
	private String gender;
	private double expectedSalary;
	private int yearsExperience;

	public CandidateDto(UUID id, String name, String email, String gender, double expectedSalary, int yearsExperience) {
		this.id = id;
		this.name = name;
		this.email = email;
		this.gender = gender;
		this.expectedSalary = expectedSalary;
		this.yearsExperience = yearsExperience;
	}
}