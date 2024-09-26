package com.seek.test.candidates.config.security;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static org.springframework.http.HttpMethod.DELETE;
import static org.springframework.http.HttpMethod.GET;
import static org.springframework.http.HttpMethod.POST;
import static org.springframework.http.HttpMethod.PUT;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
@AllArgsConstructor
public class WebSecurityConfig {

    private static final String[] AUTH_WHITELIST = {
            "/v1/security/authenticate",
            "/v3/api-docs/**",
            "/v3/api-docs",
            "/swagger-ui/**"
    };

    private final JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
    private final AuthenticationProvider authenticationProvider;
    private final JwtRequestFilter jwtRequestFilter;


    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .cors(AbstractHttpConfigurer::disable)
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests((authorize) -> {
                            authorize.requestMatchers(AUTH_WHITELIST).permitAll()
                                    .requestMatchers(POST,"/v1/security/authenticate").anonymous()
                                    .requestMatchers(GET, "/v1/candidates").hasAnyAuthority("READ_PRIVILEGE")
                                    .requestMatchers(GET, "/v1/candidates/*").hasAnyAuthority("READ_PRIVILEGE")
                                    .requestMatchers(POST, "/v1/candidates").hasAnyAuthority("WRITE_PRIVILEGE")
                                    .requestMatchers(PUT, "/v1/candidates").hasAnyAuthority("WRITE_PRIVILEGE")
                                    .requestMatchers(DELETE, "/v1/candidates").hasAnyAuthority("WRITE_PRIVILEGE")
                                    .anyRequest()
                                    .authenticated();
                        }
                )
                .exceptionHandling(aut ->
                        aut.authenticationEntryPoint(jwtAuthenticationEntryPoint))
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);

        return httpSecurity.build();
    }
}