package com.prowal.infrastructure.creditCard.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.prowal.usecases.creditCards.UpdateCreditCardUseCase;
import com.prowal.vos.v1.input.creditCard.CreditCardVOUpdateInput;

import jakarta.validation.Valid;

@RestController
public class UpdateCreditCardController {

	private final UpdateCreditCardUseCase updateCreditCardUseCase;

	public UpdateCreditCardController(UpdateCreditCardUseCase updateCreditCardUseCase) {
		super();
		this.updateCreditCardUseCase = updateCreditCardUseCase;
	}

	@PutMapping(path = "api/v1/credit-card/{creditCardId}")
	public ResponseEntity<Void> updateCreditCard(
			@RequestBody @Valid CreditCardVOUpdateInput creditCardVOUpdateInput,
			@PathVariable(name = "creditCardId") Long creditCardId) {
		updateCreditCardUseCase.execute(creditCardVOUpdateInput, creditCardId);

		return ResponseEntity.ok().build();
	}
}
