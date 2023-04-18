package com.prowal.infrastructure.auth.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.prowal.usecases.auth.SignInAuthUseCase;
import com.prowal.vos.v1.input.auth.SignInVOInput;
import com.prowal.vos.v1.output.auth.TokenVO;

import jakarta.validation.Valid;

@RestController
public class AuthSignInController {

	SignInAuthUseCase signInAuthUseCase;
	
	public AuthSignInController(SignInAuthUseCase signInAuthUseCase) {
		this.signInAuthUseCase = signInAuthUseCase;
	}
	
	@PostMapping(path = "auth/signin")
	public ResponseEntity<TokenVO> signUp(@RequestBody @Valid SignInVOInput data) {
		TokenVO tokenVO = signInAuthUseCase.signin(data.getUserName(), data.getPassword());

		return ResponseEntity.status(HttpStatus.CREATED).body(tokenVO);
	}
}
