package com.seek.test.candidates.application.dto;

import com.seek.test.candidates.domain.model.Candidate;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;


@Mapper(
		componentModel = "spring",
		nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
		unmappedTargetPolicy = ReportingPolicy.IGNORE,
		nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS
		)
public
interface CandidateMapper {

	CandidateDto toDto(Candidate Candidate);

	Candidate toModel(CandidateRequest Candidate);

	void updateModel(@MappingTarget Candidate candidate, CandidateUpdateRequest updateCandidate);

}
