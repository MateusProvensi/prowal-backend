package com.prowal.usecases.creditCards;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.prowal.entities.creditCard.gateway.CreditCardGateway;
import com.prowal.infrastructure.config.db.schema.user.UserSchema;
import com.prowal.usecases.belongsValidation.CreditCardValidation;

@Service
public class DeleteCreditCardUseCase {

	private CreditCardGateway creditCardGateway;
	private CreditCardValidation creditCardValidation;

	public DeleteCreditCardUseCase(CreditCardGateway creditCardGateway, CreditCardValidation creditCardValidation) {
		super();
		this.creditCardGateway = creditCardGateway;
		this.creditCardValidation = creditCardValidation;
	}

	public void execute(Long idCreditCard) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		UserSchema userDetails = (UserSchema) authentication.getPrincipal();

		creditCardValidation.verifyIfTheUserEntityIsTheSameOfTheCurrentUser(userDetails, idCreditCard);

		creditCardGateway.deleteCreditCard(idCreditCard);
	}
}
