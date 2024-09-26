package com.seek.test.candidates;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.seek.test")
public class CandidateApplication {

	public static void main(String[] args) {
		SpringApplication.run(com.seek.test.candidates.CandidateApplication.class, args);
	}

}
