package com.prowal.infrastructure.transaction.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.prowal.usecases.transaction.CreateNormalTransactionUseCase;
import com.prowal.vos.v1.input.transaction.TransactionVOCreateCommonInput;

import jakarta.validation.Valid;

@RestController
public class CreateNormalTransacationController {

	private final CreateNormalTransactionUseCase createNormalTransactionUseCase;

	public CreateNormalTransacationController(CreateNormalTransactionUseCase createNormalTransactionUseCase) {
		super();
		this.createNormalTransactionUseCase = createNormalTransactionUseCase;
	}

	@PostMapping(value = "api/v1/transaction/normal-transaction")
	public ResponseEntity<Void> createNormalTransaction(
			@Valid @RequestBody TransactionVOCreateCommonInput transactionVOCreateInput) {
		createNormalTransactionUseCase.execute(transactionVOCreateInput);

		return ResponseEntity.status(HttpStatus.CREATED).build();
	}

}
