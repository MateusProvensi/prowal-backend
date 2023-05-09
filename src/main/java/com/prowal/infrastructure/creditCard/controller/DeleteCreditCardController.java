package com.prowal.infrastructure.creditCard.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.prowal.usecases.creditCards.DeleteCreditCardUseCase;

@RestController
public class DeleteCreditCardController {

	private final DeleteCreditCardUseCase deleteCreditCardUseCase;

	public DeleteCreditCardController(DeleteCreditCardUseCase deleteCreditCardUseCase) {
		super();
		this.deleteCreditCardUseCase = deleteCreditCardUseCase;
	}

	@DeleteMapping(path = "api/v1/credit-card/{creditCardId}")
	public ResponseEntity<Void> deleteCreditCard(@PathVariable(name = "creditCardId") Long creditCardId) {
		deleteCreditCardUseCase.execute(creditCardId);		
		
		return ResponseEntity.status(HttpStatus.OK).build();
	}
	
}
