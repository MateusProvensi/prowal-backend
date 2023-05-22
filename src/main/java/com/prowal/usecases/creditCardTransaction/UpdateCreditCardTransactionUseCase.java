package com.prowal.usecases.creditCardTransaction;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.prowal.entities.creditCardTransaction.gateway.CreditCardTransactionGateway;
import com.prowal.infrastructure.config.db.schema.user.UserSchema;
import com.prowal.usecases.belongsValidation.CreditCardValidation;
import com.prowal.vos.v1.input.creditCardTransaction.CreditCardTransactionVOUpdateInput;

@Service
public class UpdateCreditCardTransactionUseCase {

	private CreditCardTransactionGateway creditCardTransactionGateway;
	private CreditCardValidation creditCardValidation;

	public UpdateCreditCardTransactionUseCase(
			CreditCardTransactionGateway creditCardTransactionGateway,
			CreditCardValidation creditCardValidation) {
		super();
		this.creditCardTransactionGateway = creditCardTransactionGateway;
		this.creditCardValidation = creditCardValidation;
	}

	public void execute(CreditCardTransactionVOUpdateInput creditCardTransactionVOInput, Long creditCardTransactionId) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		UserSchema userDetails = (UserSchema) authentication.getPrincipal();

		Long creditCardId = creditCardTransactionVOInput.getCreditCard().getId();

		creditCardValidation.verifyIfTheUserEntityIsTheSameOfTheCurrentUser(userDetails, creditCardId);

		creditCardTransactionVOInput.setId(creditCardTransactionId);

		creditCardTransactionGateway.updateCreditCardTransaction(creditCardTransactionVOInput);
	}
}
