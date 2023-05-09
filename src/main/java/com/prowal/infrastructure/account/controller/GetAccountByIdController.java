package com.prowal.infrastructure.account.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.prowal.usecases.account.GetAccountByIdUseCase;
import com.prowal.vos.v1.output.account.AccountVOOutput;

@RestController
public class GetAccountByIdController {

	private final GetAccountByIdUseCase getAccountByIdUseCase;

	public GetAccountByIdController(GetAccountByIdUseCase getAccountByIdUseCase) {
		this.getAccountByIdUseCase = getAccountByIdUseCase;
	}
	
	@GetMapping(path = "api/v1/accounts/{accountId}")
	public ResponseEntity<AccountVOOutput> getAccountById(@PathVariable(name = "accountId") Long accountId) {
		AccountVOOutput accountVOOutput = getAccountByIdUseCase.execute(accountId);
		
		return ResponseEntity.status(HttpStatus.OK).body(accountVOOutput);
	}
}
