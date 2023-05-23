package com.prowal.infrastructure.transaction.controller;

import java.time.Instant;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.prowal.usecases.transaction.GetTransacitionsByPeriodAndAccountUseCase;
import com.prowal.vos.v1.output.transaction.TransactionVoOutput;

@RestController
public class GetTransactionByPeriodAndAccountId {

	private final GetTransacitionsByPeriodAndAccountUseCase getTransacitionsByPeriodAndAccountUseCase;

	public GetTransactionByPeriodAndAccountId(
			GetTransacitionsByPeriodAndAccountUseCase getTransacitionsByPeriodAndAccountUseCase) {
		super();
		this.getTransacitionsByPeriodAndAccountUseCase = getTransacitionsByPeriodAndAccountUseCase;
	}

	@GetMapping(value = "api/v1/transaction/by-account/{idAccount}")
	public ResponseEntity<List<TransactionVoOutput>> findTransactionsByPeriodAndAccount(
			@RequestParam(name = "initialDate", required = false) Instant initialDate,
			@RequestParam(name = "finalDate", required = false) Instant finalDate,
			@PathVariable(name = "idAccount") Long idAccount) {

		List<TransactionVoOutput> trasactions = getTransacitionsByPeriodAndAccountUseCase
				.execute(initialDate, finalDate, idAccount);

		return ResponseEntity.ok(trasactions);
	}
}
