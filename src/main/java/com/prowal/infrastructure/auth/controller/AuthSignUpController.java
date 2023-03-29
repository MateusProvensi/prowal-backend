package com.prowal.infrastructure.auth.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.prowal.entities.auth.model.vo.v1.SignUpVO;
import com.prowal.entities.auth.model.vo.v1.TokenVO;
import com.prowal.usecases.auth.SignUpAuthUseCase;

@RestController
public class AuthSignUpController {

	SignUpAuthUseCase signUpAuthUseCase;
	
	public AuthSignUpController(SignUpAuthUseCase signUpAuthUseCase) {
		this.signUpAuthUseCase = signUpAuthUseCase;
	}

	@SuppressWarnings("rawtypes")
	@PostMapping(path = "/auth/signup")
	public ResponseEntity signUp(@RequestBody SignUpVO data) {
		if (checkIfParamsIsNotNull(data)) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Some fields are not filled");
		}

		TokenVO tokenVO = signUpAuthUseCase.signUp(data);

		return ResponseEntity.status(HttpStatus.CREATED).body(tokenVO);
	}

	private boolean checkIfParamsIsNotNull(SignUpVO data) {
		return data == null || data.getUserName() == null || data.getUserName().isBlank() || data.getPassword() == null
				|| data.getPassword().isBlank() || data.getFirstName() == null || data.getFirstName().isBlank()
				|| data.getLastName() == null || data.getLastName().isBlank();
	}
}
