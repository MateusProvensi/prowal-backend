package com.prowal.infrastructure.account.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.prowal.usecases.account.DeleteAccountByIdUseCase;

@RestController
public class DeleteAccountController {

	private final DeleteAccountByIdUseCase deleteAccountByIdUseCase;

	public DeleteAccountController(DeleteAccountByIdUseCase deleteAccountByIdUseCase) {
		this.deleteAccountByIdUseCase = deleteAccountByIdUseCase;
	}
	
	@DeleteMapping(path = "api/v1/accounts/{accountId}")
	public ResponseEntity<Void> deleteAccount(@PathVariable(name = "accountId") Long accountId) {
		deleteAccountByIdUseCase.execute(accountId);
		
		return ResponseEntity.status(HttpStatus.OK).build();
	}
	
}
