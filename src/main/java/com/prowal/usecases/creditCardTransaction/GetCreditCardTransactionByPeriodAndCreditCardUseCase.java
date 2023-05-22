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

	public List<CreditCardTransactionVOOutput> execute(Instant dataInicial, Instant dataFinal, Long idCreditCard) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		UserSchema userDetails = (UserSchema) authentication.getPrincipal();

		creditCardValidation.verifyIfTheUserEntityIsTheSameOfTheCurrentUser(userDetails, idCreditCard);

		dataInicial = dataInicial == null ? FunctionsDateUtils.currentMonthStartDay() : dataInicial;
		dataFinal = dataFinal == null ? FunctionsDateUtils.currentMonthFinalDay() : dataFinal;

		return creditCardTransactionGateway.findByCreditCardIdAndPeriod(idCreditCard, dataInicial, dataFinal);
	}
}
