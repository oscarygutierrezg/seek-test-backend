package com.seek.test.candidates.application.dto.security.jwt;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class JwtResponse implements Serializable {

	private static final long serialVersionUID = -8091879091924046844L;
	private String token;
}
