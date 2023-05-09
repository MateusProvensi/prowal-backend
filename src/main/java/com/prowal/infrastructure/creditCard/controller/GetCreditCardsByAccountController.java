package com.prowal.infrastructure.creditCard.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.prowal.usecases.creditCards.GetCreditCardsByAccountUseCase;
import com.prowal.vos.v1.output.creditCard.CreditCardVOOutput;

@RestController
public class GetCreditCardsByAccountController {

	private final GetCreditCardsByAccountUseCase getCreditCardsByAccountUseCase;

	public GetCreditCardsByAccountController(GetCreditCardsByAccountUseCase getCreditCardsByAccountUseCase) {
		super();
		this.getCreditCardsByAccountUseCase = getCreditCardsByAccountUseCase;
	}
	
	@GetMapping(path = "api/v1/credit-card/account/{accountId}")
	public ResponseEntity<List<CreditCardVOOutput>> getCreditCardsByAccount(@PathVariable(name = "accountId") Long accountId) {
		List<CreditCardVOOutput> creditCards = getCreditCardsByAccountUseCase.execute(accountId);
		
		return ResponseEntity.status(HttpStatus.OK).body(creditCards);
	}
}
