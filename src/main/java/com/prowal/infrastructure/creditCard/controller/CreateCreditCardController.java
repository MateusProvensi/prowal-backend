package com.prowal.infrastructure.creditCard.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.prowal.usecases.creditCards.CreateCreditCardUseCase;
import com.prowal.vos.v1.input.creditCard.CreditCardVOCreateInput;

import jakarta.validation.Valid;

@RestController
public class CreateCreditCardController {

	private final CreateCreditCardUseCase createCreditCardUseCase;

	public CreateCreditCardController(CreateCreditCardUseCase createCreditCardUseCase) {
		super();
		this.createCreditCardUseCase = createCreditCardUseCase;
	}
	
	@PostMapping(path = "api/v1/credit-card")
	public ResponseEntity<Void> createCreditCard(@RequestBody @Valid CreditCardVOCreateInput creditCardVOCreateInput) {
		createCreditCardUseCase.execute(creditCardVOCreateInput);
		
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}
}
