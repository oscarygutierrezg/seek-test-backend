package com.seek.test.candidates.application.dto.security.login;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class LoginRequest {

	@NotNull(message = "es obligatorio")
	@NotEmpty(message = "no debe ser vacío")
	@Size(min =1, max =50, message = "la cantidad de caracteres de estar entre 1 y 50")
	@Pattern(regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$", message = "Formato de email no válido")
	private String email;
	@NotNull(message = "es obligatorio")
	@NotEmpty(message = "no debe ser vacío")
	@Size(min =1, max =50, message = "la cantidad de caracteres de estar entre 1 y 50")
	private String password;
}
