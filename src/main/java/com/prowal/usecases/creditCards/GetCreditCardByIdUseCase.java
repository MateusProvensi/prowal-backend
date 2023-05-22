package com.prowal.usecases.creditCards;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.prowal.entities.creditCard.gateway.CreditCardGateway;
import com.prowal.infrastructure.config.db.schema.user.UserSchema;
import com.prowal.usecases.belongsValidation.CreditCardValidation;
import com.prowal.vos.v1.output.creditCard.CreditCardVOOutput;

@Service
public class GetCreditCardByIdUseCase {

	private CreditCardGateway creditCardGateway;
	private CreditCardValidation creditCardValidation;

	public GetCreditCardByIdUseCase(CreditCardGateway creditCardGateway, CreditCardValidation creditCardValidation) {
		super();
		this.creditCardGateway = creditCardGateway;
		this.creditCardValidation = creditCardValidation;
	}

	public CreditCardVOOutput execute(Long idCreditCard) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		UserSchema userDetails = (UserSchema) authentication.getPrincipal();

		creditCardValidation.verifyIfTheUserEntityIsTheSameOfTheCurrentUser(userDetails, idCreditCard);

		CreditCardVOOutput creditCard = creditCardGateway.findById(idCreditCard);

		return creditCard;
	}
}
