package com.seek.test.candidates.util;

import com.seek.test.candidates.application.dto.security.login.LoginRequest;

public class UserUtil {



	public LoginRequest createUsuarioRequestLogin() {
		return  LoginRequest.builder()
				.email("test@test.com")
				.password("test")
				.build();
	}
}
