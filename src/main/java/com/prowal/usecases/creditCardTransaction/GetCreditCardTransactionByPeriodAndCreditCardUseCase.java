package com.prowal.usecases.creditCardTransaction;

import java.time.Instant;
import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.prowal.entities.creditCardTransaction.gateway.CreditCardTransactionGateway;
import com.prowal.infrastructure.config.db.schema.user.UserSchema;
import com.prowal.usecases.belongsValidation.CreditCardValidation;
import com.prowal.vos.v1.output.creditCardTransaction.CreditCardTransactionVOOutput;

import core.FunctionsDateUtils;

@Service
public class GetCreditCardTransactionByPeriodAndCreditCardUseCase {

	private CreditCardTransactionGateway creditCardTransactionGateway;
	private CreditCardValidation creditCardValidation;

	public GetCreditCardTransactionByPeriodAndCreditCardUseCase(
			CreditCardTransactionGateway creditCardTransactionGateway,
			CreditCardValidation creditCardValidation) {
		super();
		this.creditCardTransactionGateway = creditCardTransactionGateway;
		this.creditCardValidation = creditCardValidation;
	}

	public List<CreditCardTransactionVOOutput> execute(Instant initialDate, Instant finalDate, Long idCreditCard) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		UserSchema userDetails = (UserSchema) authentication.getPrincipal();

		creditCardValidation.verifyIfTheUserEntityIsTheSameOfTheCurrentUser(userDetails, idCreditCard);

		initialDate = initialDate == null ? FunctionsDateUtils.currentMonthStartDay() : initialDate;
		finalDate = finalDate == null ? FunctionsDateUtils.currentMonthFinalDay() : finalDate;

		return creditCardTransactionGateway.findByCreditCardIdAndPeriod(idCreditCard, initialDate, finalDate);
	}
}
