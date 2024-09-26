package com.seek.test.candidates.infrastructure.adapter.security;


import com.seek.test.candidates.application.dto.security.jwt.JwtResponse;
import com.seek.test.candidates.application.dto.security.login.LoginRequest;
import com.seek.test.candidates.application.service.security.JwtUserDetailsService;
import com.seek.test.candidates.config.security.JwtTokenUtil;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/v1/security")
@Tag(name = "Security Controller", description = "Se encarga de manejar el login de los usuarios.")
@CrossOrigin
@AllArgsConstructor
public class SecurityController {

	private final AuthenticationManager authenticationManager;
	private final JwtTokenUtil jwtTokenUtil;
	private final JwtUserDetailsService userDetailsService;

	@PostMapping(value = "/authenticate")
	public ResponseEntity<JwtResponse> createAuthenticationToken(
			@Valid @RequestBody LoginRequest loginRequest) throws Exception {

		authenticate(loginRequest.getEmail(), loginRequest.getPassword());

		final UserDetails userDetails = userDetailsService
				.loadUserByUsername(loginRequest.getEmail());

		final String token = jwtTokenUtil.generateToken(userDetails);

		return ResponseEntity.ok(new JwtResponse(token));
	}


	private void authenticate(String username, String password) throws Exception {
		try {
			UsernamePasswordAuthenticationToken authenticationToken = new  UsernamePasswordAuthenticationToken(username, password);
			authenticationManager.authenticate(authenticationToken);
		} catch (DisabledException e) {
			throw new Exception("USER_DISABLED", e);
		} catch (BadCredentialsException e) {
			e.printStackTrace();
			throw new Exception("INVALID_CREDENTIALS", e);
		}
	}
}
