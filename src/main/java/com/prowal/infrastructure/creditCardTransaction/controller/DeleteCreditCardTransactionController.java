package com.prowal.infrastructure.creditCardTransaction.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.prowal.usecases.creditCardTransaction.DeleteCreditCardTransactionUseCase;

@RestController
public class DeleteCreditCardTransactionController {

	private DeleteCreditCardTransactionUseCase deleteCreditCardTransactionUseCase;

	public DeleteCreditCardTransactionController(
			DeleteCreditCardTransactionUseCase deleteCreditCardTransactionUseCase) {
		super();
		this.deleteCreditCardTransactionUseCase = deleteCreditCardTransactionUseCase;
	}

	@DeleteMapping(value = "api/v1/credit-card-transaction/{idTransaction}")
	public ResponseEntity<Void> deleteCreditCardTransaction(@PathVariable(name = "idTransaction") Long idTransaction) {
		deleteCreditCardTransactionUseCase.execute(idTransaction);

		return ResponseEntity.ok().build();
	}
}
