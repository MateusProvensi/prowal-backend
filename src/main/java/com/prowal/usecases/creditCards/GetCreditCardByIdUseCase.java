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
public class GetCreditCardByIdUseCase {

	private CreditCardGateway creditCardGateway;
	private AccountGateway accountGateway;

	public GetCreditCardByIdUseCase(CreditCardGateway creditCardGateway, AccountGateway accountGateway) {
		super();
		this.creditCardGateway = creditCardGateway;
		this.accountGateway = accountGateway;
	}

	public CreditCardVOOutput execute(Long idCreditCard) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		UserSchema userDetails = (UserSchema) authentication.getPrincipal();

		Long userId = userDetails.getId();
		
		CreditCardVOOutput creditCard = creditCardGateway.findById(idCreditCard);
		
		Long accountIdToFind = creditCard.getAccount().getKey();
		
		AccountVOOutput accountVOOutput = accountGateway.findById(accountIdToFind);

		Long userIdCategory = accountVOOutput.getUser().getKey();

		if (userId != userIdCategory) {
			throw new UserDoesNotTheSameOfTheEntity("The user of this account is not the same of the current user");
		}

		return creditCard;
	}
}
