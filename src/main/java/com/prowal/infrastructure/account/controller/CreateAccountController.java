package com.prowal.infrastructure.account.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.prowal.usecases.account.CreateAccountUseCase;
import com.prowal.vos.v1.input.account.AccountVOCreateInput;

import jakarta.validation.Valid;

@RestController
public class CreateAccountController {

	private final CreateAccountUseCase createAccountUseCase;

	public CreateAccountController(CreateAccountUseCase createAccountUseCase) {
		this.createAccountUseCase = createAccountUseCase;
	}

	@PostMapping(path = "api/v1/accounts")
	public ResponseEntity<Void> createAnAccount(@RequestBody @Valid AccountVOCreateInput accountVOCreateInput) {
		createAccountUseCase.execute(accountVOCreateInput);
		
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}
}
