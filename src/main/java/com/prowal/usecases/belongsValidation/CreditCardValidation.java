package com.prowal.usecases.belongsValidation;

import org.springframework.stereotype.Service;

import com.prowal.entities.creditCard.gateway.CreditCardGateway;
import com.prowal.infrastructure.config.db.schema.user.UserSchema;
import com.prowal.infrastructure.config.exceptions.UserDoesNotTheSameOfTheEntity;
import com.prowal.vos.v1.output.creditCard.CreditCardVOOutput;

@Service
public class CreditCardValidation {

	private CreditCardGateway creditCardGateway;

	public CreditCardValidation(CreditCardGateway creditCardGateway) {
		super();
		this.creditCardGateway = creditCardGateway;
	}
	
	public void verifyIfTheUserEntityIsTheSameOfTheCurrentUser(UserSchema currentUser, Long idCreditCard) {
		CreditCardVOOutput creditCardVOOutput = creditCardGateway.findById(idCreditCard);
		Long userId = currentUser.getId();
		
		if (creditCardVOOutput.getAccount().getUser().getKey() != userId) {
			throw new UserDoesNotTheSameOfTheEntity("The user of this credit card is not the same of the current user");
		}
	}
}
