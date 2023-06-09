package com.prowal.infrastructure.auth.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.prowal.usecases.auth.SignUpAuthUseCase;
import com.prowal.vos.v1.input.auth.SignUpVOInput;
import com.prowal.vos.v1.output.auth.TokenVO;

import jakarta.validation.Valid;

@RestController
public class AuthSignUpController {

	SignUpAuthUseCase signUpAuthUseCase;
	
	public AuthSignUpController(SignUpAuthUseCase signUpAuthUseCase) {
		this.signUpAuthUseCase = signUpAuthUseCase;
	}

	@PostMapping(path = "auth/signup")
	public ResponseEntity<TokenVO> signUp(@RequestBody @Valid SignUpVOInput data) {
		TokenVO tokenVO = signUpAuthUseCase.signUp(data);

		return ResponseEntity.status(HttpStatus.CREATED).body(tokenVO);
	}
}
