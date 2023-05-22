package com.prowal.infrastructure.creditCardTransaction.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.prowal.usecases.creditCardTransaction.UpdateCreditCardTransactionUseCase;
import com.prowal.vos.v1.input.creditCardTransaction.CreditCardTransactionVOUpdateInput;

import jakarta.validation.Valid;

@RestController
public class UpdateCreditCardTransactionController {

	private UpdateCreditCardTransactionUseCase updateCreditCardTransactionUseCase;

	public UpdateCreditCardTransactionController(
			UpdateCreditCardTransactionUseCase updateCreditCardTransactionUseCase) {
		super();
		this.updateCreditCardTransactionUseCase = updateCreditCardTransactionUseCase;
	}

	@PutMapping(value = "api/v1/credit-card-transaction/{idTransaction}")
	public ResponseEntity<Void> updateCreditCardTransaction(
			@Valid @RequestBody CreditCardTransactionVOUpdateInput creditCardTransactionVOUpdateInput,
			@PathVariable(name = "idTransaction") Long idTransaction) {
		updateCreditCardTransactionUseCase.execute(creditCardTransactionVOUpdateInput, idTransaction);

		return ResponseEntity.ok().build();
	}
}
