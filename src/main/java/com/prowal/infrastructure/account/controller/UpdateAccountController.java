package com.prowal.infrastructure.account.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.prowal.usecases.account.UpdateAccountUseCase;
import com.prowal.vos.v1.input.account.AccountVOUpdateInput;

import jakarta.validation.Valid;

@RestController
public class UpdateAccountController {

	private final UpdateAccountUseCase updateAccountUseCase;

	public UpdateAccountController(UpdateAccountUseCase updateAccountUseCase) {
		this.updateAccountUseCase = updateAccountUseCase;
	}

	@PutMapping(path = "api/accounts/{accountId}")
	public ResponseEntity<Void> updateAccount(
			@PathVariable(name = "accountId") Long accountId,
			@RequestBody @Valid AccountVOUpdateInput accountVOUpdateInput) {
		updateAccountUseCase.execute(accountVOUpdateInput, accountId);

		return ResponseEntity.status(HttpStatus.OK).build();
	}
}
