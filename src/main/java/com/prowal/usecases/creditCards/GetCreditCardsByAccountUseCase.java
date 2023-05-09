package com.prowal.usecases.creditCards;

import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.prowal.entities.account.gateway.AccountGateway;
import com.prowal.entities.creditCard.gateway.CreditCardGateway;
import com.prowal.infrastructure.config.db.schema.user.UserSchema;
import com.prowal.infrastructure.config.exceptions.EntityExistsException;
import com.prowal.infrastructure.config.exceptions.UserDoesNotTheSameOfTheEntity;
import com.prowal.vos.v1.output.account.AccountVOOutput;
import com.prowal.vos.v1.output.creditCard.CreditCardVOOutput;

@Service
public class GetCreditCardsByAccountUseCase {

	private CreditCardGateway creditCardGateway;
	private AccountGateway accountGateway;

	public GetCreditCardsByAccountUseCase(CreditCardGateway creditCardGateway, AccountGateway accountGateway) {
		super();
		this.creditCardGateway = creditCardGateway;
		this.accountGateway = accountGateway;
	}

	public List<CreditCardVOOutput> execute(Long idAccount) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		UserSchema userDetails = (UserSchema) authentication.getPrincipal();

		Long userId = userDetails.getId();

		AccountVOOutput accountVOOutput = accountGateway.findById(idAccount);

		Long userIdAccount = accountVOOutput.getUser().getKey();

		if (userId != userIdAccount) {
			throw new UserDoesNotTheSameOfTheEntity("The user of this account is not the same of the current user");
		}

		List<CreditCardVOOutput> creditCards = creditCardGateway.findByAccountId(idAccount);

		if (creditCards.isEmpty()) {
			throw new EntityExistsException("No credit card exists that has a account with ID = " + idAccount + ".");
		}

		return creditCards;
	}
}
