package com.prowal.usecases.creditCards;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.prowal.entities.account.gateway.AccountGateway;
import com.prowal.entities.creditCard.gateway.CreditCardGateway;
import com.prowal.infrastructure.config.db.schema.user.UserSchema;
import com.prowal.infrastructure.config.exceptions.UserDoesNotTheSameOfTheEntity;
import com.prowal.vos.v1.output.account.AccountVOOutput;
import com.prowal.vos.v1.output.creditCard.CreditCardVOOutput;

@Service
public class DeleteCreditCardUseCase {

	private CreditCardGateway creditCardGateway;
	private AccountGateway accountGateway;

	public DeleteCreditCardUseCase(CreditCardGateway creditCardGateway, AccountGateway accountGateway) {
		super();
		this.creditCardGateway = creditCardGateway;
		this.accountGateway = accountGateway;
	}

	public void execute(Long idCreditCard) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		UserSchema userDetails = (UserSchema) authentication.getPrincipal();

		Long userId = userDetails.getId();
		
		CreditCardVOOutput creditCardVOOutput = creditCardGateway.findById(idCreditCard);
		Long accountId = creditCardVOOutput.getAccount().getKey();

		AccountVOOutput account = accountGateway.findById(accountId);

		if (account.getUser().getKey() != userId) {
			throw new UserDoesNotTheSameOfTheEntity("The user of this account is not the same of the current user");
		}

		creditCardGateway.deleteCreditCard(idCreditCard);
	}
}
