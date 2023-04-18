package com.prowal.infrastructure.auth.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.prowal.usecases.auth.RefreshTokenUseCase;
import com.prowal.vos.v1.output.auth.TokenVO;

@RestController
public class AuthRefreshTokenController {
	
	RefreshTokenUseCase refreshTokenUseCase;
	
	public AuthRefreshTokenController(RefreshTokenUseCase refreshTokenUseCase) {
		this.refreshTokenUseCase = refreshTokenUseCase;
	}
	
	@PutMapping(value = "/auth/refresh/{username}")
	public ResponseEntity<TokenVO> refreshToken(
			@PathVariable("username") String username,
			@RequestHeader("Authorization") String refreshToken) {
		TokenVO token = refreshTokenUseCase.refreshToken(username, refreshToken);

		return ResponseEntity.status(HttpStatus.OK).body(token);
	}
}
