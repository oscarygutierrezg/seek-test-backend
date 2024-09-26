package com.seek.test.candidates.config;


import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import org.springframework.context.annotation.Configuration;

@Configuration
@io.swagger.v3.oas.annotations.security.SecurityScheme(
        name = "bearerAuthentication",
        type = SecuritySchemeType.HTTP,
        bearerFormat = "JWT",
        scheme = "bearer"
)
@OpenAPIDefinition(
        info = @io.swagger.v3.oas.annotations.info.Info(title = "Candidates API", version = "v1"),
        security = @io.swagger.v3.oas.annotations.security.SecurityRequirement(name = "bearerAuthentication")
)
public class OpenApiConfig {

}