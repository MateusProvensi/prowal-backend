package com.prowal.infrastructure.account.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.prowal.usecases.account.GetAccountsByUserIdUseCase;
import com.prowal.vos.v1.output.account.AccountVOOutput;

@RestController
public class GetAccountByCurrentUserController {

	private final GetAccountsByUserIdUseCase getAccountsByUserIdUseCase;

	public GetAccountByCurrentUserController(GetAccountsByUserIdUseCase getAccountsByUserIdUseCase) {
		this.getAccountsByUserIdUseCase = getAccountsByUserIdUseCase;
	}
	
	@GetMapping(path = "api/accounts/current-user/all")
	public ResponseEntity<List<AccountVOOutput>> getAllAccountsByCurrentUser() {
		List<AccountVOOutput> accounts = getAccountsByUserIdUseCase.execute();
		
		return ResponseEntity.status(HttpStatus.OK).body(accounts);
	}
}
