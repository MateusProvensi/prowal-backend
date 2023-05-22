package com.prowal.usecases.creditCards;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.prowal.entities.creditCard.gateway.CreditCardGateway;
import com.prowal.infrastructure.config.db.schema.user.UserSchema;
import com.prowal.usecases.belongsValidation.AccountValidation;
import com.prowal.vos.v1.input.creditCard.CreditCardVOCreateInput;

@Service
public class CreateCreditCardUseCase {

	private CreditCardGateway creditCardGateway;
	private AccountValidation accountValidation;

	public CreateCreditCardUseCase(CreditCardGateway creditCardGateway, AccountValidation accountValidation) {
		super();
		this.creditCardGateway = creditCardGateway;
		this.accountValidation = accountValidation;
	}

	public void execute(CreditCardVOCreateInput creditCardVOCreateInput) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		UserSchema userDetails = (UserSchema) authentication.getPrincipal();

		Long accountId = creditCardVOCreateInput.getAccount().getId();

		accountValidation.verifyIfTheUserEntityIsTheSameOfTheCurrentUser(userDetails, accountId);

		creditCardGateway.createCreditCard(creditCardVOCreateInput);
	}
}
