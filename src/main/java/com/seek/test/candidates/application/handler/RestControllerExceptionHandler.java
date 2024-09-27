package com.seek.test.candidates.application.handler;

import com.seek.test.candidates.application.dto.ApiResponseErrorDto;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.Arrays;
import java.util.List;

@ControllerAdvice
public class RestControllerExceptionHandler {

	@ExceptionHandler(Exception.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public ResponseEntity<ApiResponseErrorDto> handleException(Exception exception){
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
				.body(
						createApiResponseErrorDto(HttpStatus.INTERNAL_SERVER_ERROR, Arrays.asList(exception.getMessage()))
						);

	}

	private ApiResponseErrorDto createApiResponseErrorDto(HttpStatus httpStatus,List<String> errors) {
		return ApiResponseErrorDto.builder()
				.code(httpStatus.value())
				.message(httpStatus.getReasonPhrase())
				.errors(errors)
				.build();
	}
}
