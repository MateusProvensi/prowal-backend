package com.prowal.infrastructure.transaction.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.prowal.usecases.transaction.CreateTransactionFromCreditCardUseCase;
import com.prowal.vos.v1.input.transaction.TransactionVOCreateFromCreditCardInput;

import jakarta.validation.Valid;

@RestController
public class CreateTransactionFromCreditCardController {

	private CreateTransactionFromCreditCardUseCase createTransactionFromCreditCardUseCase;

	public CreateTransactionFromCreditCardController(
			CreateTransactionFromCreditCardUseCase createTransactionFromCreditCardUseCase) {
		super();
		this.createTransactionFromCreditCardUseCase = createTransactionFromCreditCardUseCase;
	}

	@PostMapping(value = "api/v1/transaction/credit-card-transaction")
	public ResponseEntity<Void> createTransactionFromCreditCard(
			@Valid @RequestBody TransactionVOCreateFromCreditCardInput transactionVOCreateInput) {
		createTransactionFromCreditCardUseCase.execute(transactionVOCreateInput);

		return ResponseEntity.status(HttpStatus.CREATED).build();
	}
}
