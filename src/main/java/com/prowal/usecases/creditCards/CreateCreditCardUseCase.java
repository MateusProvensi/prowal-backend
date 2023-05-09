package com.prowal.usecases.creditCards;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.prowal.entities.account.gateway.AccountGateway;
import com.prowal.entities.creditCard.gateway.CreditCardGateway;
import com.prowal.infrastructure.config.db.schema.user.UserSchema;
import com.prowal.infrastructure.config.exceptions.UserDoesNotTheSameOfTheEntity;
import com.prowal.vos.v1.input.creditCard.CreditCardVOCreateInput;
import com.prowal.vos.v1.output.account.AccountVOOutput;

@Service
public class CreateCreditCardUseCase {

	private CreditCardGateway creditCardGateway;
	private AccountGateway accountGateway;

	public CreateCreditCardUseCase(CreditCardGateway creditCardGateway, AccountGateway accountGateway) {
		super();
		this.creditCardGateway = creditCardGateway;
		this.accountGateway = accountGateway;
	}

	public void execute(CreditCardVOCreateInput creditCardVOCreateInput) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		UserSchema userDetails = (UserSchema) authentication.getPrincipal();

		Long userId = userDetails.getId();
		Long accountId = creditCardVOCreateInput.getAccount().getId();

		AccountVOOutput account = accountGateway.findById(accountId);

		if (account.getUser().getKey() != userId) {
			throw new UserDoesNotTheSameOfTheEntity("The user of this account is not the same of the current user");
		}

		creditCardGateway.createCreditCard(creditCardVOCreateInput);
	}

}
