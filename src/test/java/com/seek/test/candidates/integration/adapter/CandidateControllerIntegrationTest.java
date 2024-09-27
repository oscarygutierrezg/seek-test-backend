package com.seek.test.candidates.integration.adapter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.seek.test.candidates.CandidateApplication;
import com.seek.test.candidates.application.dto.CandidateDto;
import com.seek.test.candidates.application.dto.CandidateRequest;
import com.seek.test.candidates.application.dto.CandidateUpdateRequest;
import com.seek.test.candidates.application.dto.security.jwt.JwtResponse;
import com.seek.test.candidates.domain.port.incoming.CandidateUseCase;
import com.seek.test.candidates.util.CandidateUtil;
import com.seek.test.candidates.util.UserUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@AutoConfigureMockMvc
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = CandidateApplication.class)
class CandidateControllerIntegrationTest {

	private static final String AUTHORIZATION = "Authorization";
	private  String token = "";

	@Autowired
	private MockMvc mockMvc;
	@Autowired
	private CandidateUseCase candidateUseCase;
	@Autowired
	private ObjectMapper objectMapper;

	private final CandidateUtil candidateUtil = new CandidateUtil();
	private UserUtil userUtil = new UserUtil();

	@BeforeEach
	void setUp() throws Exception {
		var userRequest = userUtil.createUsuarioRequestLogin();
		ResultActions res =    mockMvc.perform(
				MockMvcRequestBuilders.post("/v1/security/authenticate")
						.contentType(MediaType.APPLICATION_JSON)
						.content(objectMapper.writeValueAsString(userRequest))
						.accept(MediaType.APPLICATION_JSON)
								.header(AUTHORIZATION, "Bearer "+token)
		);

		JwtResponse jwtResponse = objectMapper.readValue(res.andReturn().getResponse().getContentAsString(), JwtResponse.class);
		token = jwtResponse.getToken();
	}

	@Test
	void test_CreateCandidate_Should_ReturnCreatedCandidate_When_ValidRequest() throws Exception {
		// Given
		CandidateRequest candidateRequest = candidateUtil.createCandidateRequest();

		// When
		ResultActions res = mockMvc.perform(
						MockMvcRequestBuilders.post("/v1/candidates")
								.contentType(MediaType.APPLICATION_JSON)
								.content(objectMapper.writeValueAsString(candidateRequest))
								.accept(MediaType.APPLICATION_JSON)
								.header(AUTHORIZATION, "Bearer "+token)
				)
				.andDo(MockMvcResultHandlers.print())
				.andExpect(MockMvcResultMatchers.status().isOk());

		// Then
		CandidateDto candidateDto = objectMapper.readValue(res.andReturn().getResponse().getContentAsString(), CandidateDto.class);
		Assertions.assertNotNull(candidateDto);
		Assertions.assertNotNull(candidateDto.getId());
	}

	@Test
	void test_GetCandidateById_Should_ReturnCandidate_When_ValidId() throws Exception {
		// Given
		CandidateDto candidateDto = candidateUtil.createAndSaveCandidate(candidateUseCase);

		// When
		ResultActions res = mockMvc.perform(
						MockMvcRequestBuilders.get("/v1/candidates/" + candidateDto.getId())
								.accept(MediaType.APPLICATION_JSON)
								.header(AUTHORIZATION, "Bearer "+token)
				)
				.andDo(MockMvcResultHandlers.print())
				.andExpect(MockMvcResultMatchers.status().isOk());

		// Then
		CandidateDto result = objectMapper.readValue(res.andReturn().getResponse().getContentAsString(), CandidateDto.class);
		Assertions.assertNotNull(result);
		Assertions.assertEquals(candidateDto.getId(), result.getId());
	}

	@Test
	void test_UpdateCandidate_Should_ReturnUpdatedCandidate_When_ValidRequest() throws Exception {
		// Given
		CandidateDto candidateDto = candidateUtil.createAndSaveCandidate(candidateUseCase);
		CandidateUpdateRequest updateRequest = candidateUtil.createCandidateUpdateRequest();

		// When
		ResultActions res = mockMvc.perform(
						MockMvcRequestBuilders.put("/v1/candidates/" + candidateDto.getId())
								.contentType(MediaType.APPLICATION_JSON)
								.content(objectMapper.writeValueAsString(updateRequest))
								.accept(MediaType.APPLICATION_JSON)
								.header(AUTHORIZATION, "Bearer "+token)
				)
				.andDo(MockMvcResultHandlers.print())
				.andExpect(MockMvcResultMatchers.status().isOk());

		// Then
		CandidateDto updatedCandidate = objectMapper.readValue(res.andReturn().getResponse().getContentAsString(), CandidateDto.class);
		Assertions.assertNotNull(updatedCandidate);
		Assertions.assertEquals(candidateDto.getId(), updatedCandidate.getId());
	}

	@Test
	void test_DeleteCandidate_Should_ReturnSuccess_When_ValidId() throws Exception {
		// Given
		CandidateDto candidateDto = candidateUtil.createAndSaveCandidate(candidateUseCase);

		// When
		ResultActions res = mockMvc.perform(
						MockMvcRequestBuilders.delete("/v1/candidates/" + candidateDto.getId())
								.accept(MediaType.APPLICATION_JSON)
								.header(AUTHORIZATION, "Bearer "+token)
				)
				.andDo(MockMvcResultHandlers.print())
				.andExpect(MockMvcResultMatchers.status().isOk());

		// Then
		String response = res.andReturn().getResponse().getContentAsString();
		Assertions.assertTrue(response.contains("\"deleted\":true"));
	}

	@Test
	void test_GetAllCandidates_Should_ReturnCandidateList_When_Invoked() throws Exception {
		// Given
		candidateUtil.createAndSaveCandidate(candidateUseCase);
		candidateUtil.createAndSaveCandidate(candidateUseCase);

		// When
		ResultActions res = mockMvc.perform(
						MockMvcRequestBuilders.get("/v1/candidates")
								.accept(MediaType.APPLICATION_JSON)
								.header(AUTHORIZATION, "Bearer "+token)
				)
				.andDo(MockMvcResultHandlers.print())
				.andExpect(MockMvcResultMatchers.status().isOk());

		// Then
		String response = res.andReturn().getResponse().getContentAsString();
		Assertions.assertTrue(response.contains("["));
		Assertions.assertTrue(response.contains("]"));
	}




}


