package com.prowal.infrastructure.creditCardTransaction.controller;

import java.time.Instant;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.prowal.usecases.creditCardTransaction.GetCreditCardTransactionByPeriodAndCreditCardUseCase;
import com.prowal.vos.v1.output.creditCardTransaction.CreditCardTransactionVOOutput;

@RestController
public class GetCreditCardTransactionsByPeriodAndCreditCardController {

	private GetCreditCardTransactionByPeriodAndCreditCardUseCase getCreditCardTransactionByPeriodAndCreditCardUseCase;

	public GetCreditCardTransactionsByPeriodAndCreditCardController(
			GetCreditCardTransactionByPeriodAndCreditCardUseCase getCreditCardTransactionByPeriodAndCreditCard) {
		super();
		this.getCreditCardTransactionByPeriodAndCreditCardUseCase = getCreditCardTransactionByPeriodAndCreditCard;
	}

	@GetMapping(value = "api/v1/credit-card-transaction/card/{idCreditCard}")
	public ResponseEntity<List<CreditCardTransactionVOOutput>> getTransactionsByPeriod(
			@RequestParam(name = "initialDate", required = false) Instant initialDate,
			@RequestParam(name = "finalDate", required = false) Instant finalDate,
			@PathVariable(name = "idCreditCard") Long idCreditCard) {

		List<CreditCardTransactionVOOutput> transactions = getCreditCardTransactionByPeriodAndCreditCardUseCase
				.execute(initialDate, finalDate, idCreditCard);

		return ResponseEntity.ok(transactions);
	}
}
