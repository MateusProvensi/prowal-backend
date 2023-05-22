package com.prowal.infrastructure.transaction.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.prowal.usecases.transaction.CreateTransferTransactionUseCase;
import com.prowal.vos.v1.input.transaction.TransactionVOCreateTransferInput;

import jakarta.validation.Valid;

@RestController
public class CreateTransferTransactionController {

	private final CreateTransferTransactionUseCase createTransferTransactionUseCase;

	public CreateTransferTransactionController(CreateTransferTransactionUseCase createTransferTransactionUseCase) {
		super();
		this.createTransferTransactionUseCase = createTransferTransactionUseCase;
	}

	@PostMapping(value = "api/v1/transaction/transfer")
	public ResponseEntity<Void> createTransferTransacation(
			@Valid @RequestBody TransactionVOCreateTransferInput transactionVOCreateTransferInput) {
		createTransferTransactionUseCase.execute(transactionVOCreateTransferInput);

		return ResponseEntity.status(HttpStatus.CREATED).build();
	}
}
