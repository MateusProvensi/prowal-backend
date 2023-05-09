package com.prowal.infrastructure.creditCard.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.prowal.usecases.creditCards.GetCreditCardByIdUseCase;
import com.prowal.vos.v1.output.creditCard.CreditCardVOOutput;

@RestController
public class GetCreditCardByIdController {

	private final GetCreditCardByIdUseCase getCreditCardByIdUseCase;

	public GetCreditCardByIdController(GetCreditCardByIdUseCase getCreditCardByIdUseCase) {
		super();
		this.getCreditCardByIdUseCase = getCreditCardByIdUseCase;
	}

	@GetMapping(path = "api/v1/credit-card/{creditCardId}")
	public ResponseEntity<CreditCardVOOutput> getCreditCardById(
			@PathVariable(name = "creditCardId") Long creditCardId) {
		CreditCardVOOutput creditCardVOOutput = getCreditCardByIdUseCase.execute(creditCardId);

		return ResponseEntity.status(HttpStatus.OK).body(creditCardVOOutput);
	}
}
