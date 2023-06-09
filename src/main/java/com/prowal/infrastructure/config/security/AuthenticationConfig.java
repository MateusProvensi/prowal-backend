package com.prowal.infrastructure.config.security;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.DelegatingPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder.SecretKeyFactoryAlgorithm;
import org.springframework.security.web.SecurityFilterChain;

import com.prowal.infrastructure.config.security.jwt.JwtConfigurer;
import com.prowal.infrastructure.config.security.jwt.JwtTokenProvider;

@EnableWebSecurity
@Configuration
public class AuthenticationConfig {

	@Autowired
	private JwtTokenProvider tokenProvider;
	
	@Bean
	PasswordEncoder passwordEncoder() {
		Map<String, PasswordEncoder> enconders = new HashMap<>();

		Pbkdf2PasswordEncoder pbkdf2Enconder = new Pbkdf2PasswordEncoder(
				"",
				8,
				185000,
				SecretKeyFactoryAlgorithm.PBKDF2WithHmacSHA256);

		enconders.put("pbkdf2", pbkdf2Enconder);

		DelegatingPasswordEncoder passwordEncoder = new DelegatingPasswordEncoder("pbkdf2", enconders);

		passwordEncoder.setDefaultPasswordEncoderForMatches(pbkdf2Enconder);

		return passwordEncoder;
	}

	
	@Bean
	AuthenticationManager authenticationManagerBean(AuthenticationConfiguration authenticationConfiguration)
			throws Exception {
		return authenticationConfiguration.getAuthenticationManager();
	}
	
	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		return http
				.httpBasic()
				.disable()
				.csrf(AbstractHttpConfigurer::disable)
				.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
				.authorizeHttpRequests(
						authorizeHttpRequests -> authorizeHttpRequests
								.requestMatchers(
										"/auth/signup",
										"/auth/signin",
										"/auth/refresh/**",
										"/swagger-ui/**",
										"/v3/api-docs/**")
								.permitAll()
								.requestMatchers("/api/**")
								.authenticated()
								.requestMatchers("/users")
								.denyAll())
				.cors()
				.and()
				.apply(new JwtConfigurer(tokenProvider))
				.and()
				.build();
	}
	

}
