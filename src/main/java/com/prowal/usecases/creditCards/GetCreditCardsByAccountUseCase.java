package com.prowal.usecases.creditCards;

import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.prowal.entities.creditCard.gateway.CreditCardGateway;
import com.prowal.infrastructure.config.db.schema.user.UserSchema;
import com.prowal.infrastructure.config.exceptions.EntityExistsException;
import com.prowal.usecases.belongsValidation.AccountValidation;
import com.prowal.vos.v1.output.creditCard.CreditCardVOOutput;

@Service
public class GetCreditCardsByAccountUseCase {

	private CreditCardGateway creditCardGateway;
	private AccountValidation accountValidation;

	public GetCreditCardsByAccountUseCase(CreditCardGateway creditCardGateway, AccountValidation accountValidation) {
		super();
		this.creditCardGateway = creditCardGateway;
		this.accountValidation = accountValidation;
	}

	public List<CreditCardVOOutput> execute(Long idAccount) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		UserSchema userDetails = (UserSchema) authentication.getPrincipal();

		accountValidation.verifyIfTheUserEntityIsTheSameOfTheCurrentUser(userDetails, idAccount);

		List<CreditCardVOOutput> creditCards = creditCardGateway.findByAccountId(idAccount);

		if (creditCards.isEmpty()) {
			throw new EntityExistsException("No credit card exists that has a account with ID = " + idAccount + ".");
		}

		return creditCards;
	}
}
