package com.prowal.usecases.creditCards;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.prowal.entities.creditCard.gateway.CreditCardGateway;
import com.prowal.infrastructure.config.db.schema.user.UserSchema;
import com.prowal.usecases.belongsValidation.CreditCardValidation;
import com.prowal.vos.v1.input.creditCard.CreditCardVOUpdateInput;

@Service
public class UpdateCreditCardUseCase {

	private CreditCardGateway creditCardGateway;
	private CreditCardValidation creditCardValidation;

	public UpdateCreditCardUseCase(CreditCardGateway creditCardGateway, CreditCardValidation creditCardValidation) {
		super();
		this.creditCardGateway = creditCardGateway;
		this.creditCardValidation = creditCardValidation;
	}

	public void execute(CreditCardVOUpdateInput creditCardVOUpdateInput, Long idCreditCard) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		UserSchema userDetails = (UserSchema) authentication.getPrincipal();

		creditCardValidation.verifyIfTheUserEntityIsTheSameOfTheCurrentUser(userDetails, idCreditCard);

		creditCardVOUpdateInput.setId(idCreditCard);

		creditCardGateway.updateCreditCard(creditCardVOUpdateInput);
	}
}
