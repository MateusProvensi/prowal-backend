package com.prowal.usecases.creditCards;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.prowal.entities.account.gateway.AccountGateway;
import com.prowal.entities.creditCard.gateway.CreditCardGateway;
import com.prowal.infrastructure.config.db.schema.user.UserSchema;
import com.prowal.infrastructure.config.exceptions.UserDoesNotTheSameOfTheEntity;
import com.prowal.vos.v1.input.creditCard.CreditCardVOUpdateInput;
import com.prowal.vos.v1.output.account.AccountVOOutput;
import com.prowal.vos.v1.output.creditCard.CreditCardVOOutput;

@Service
public class UpdateCreditCardUseCase {

	private CreditCardGateway creditCardGateway;
	private AccountGateway accountGateway;

	public UpdateCreditCardUseCase(CreditCardGateway creditCardGateway, AccountGateway accountGateway) {
		super();
		this.creditCardGateway = creditCardGateway;
		this.accountGateway = accountGateway;
	}

	public void execute(CreditCardVOUpdateInput creditCardVOUpdateInput, Long idCreditCard) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		UserSchema userDetails = (UserSchema) authentication.getPrincipal();

		Long userId = userDetails.getId();

		CreditCardVOOutput subCategory = creditCardGateway.findById(idCreditCard);

		Long categoryIdToFind = subCategory.getAccount().getKey();
		Long categoryIdToFindInsideVOAlter = creditCardVOUpdateInput.getAccount().getId();

		AccountVOOutput categoryVOOutput = accountGateway.findById(categoryIdToFind);
		AccountVOOutput categoryVOOutputInTheSubCategoryToAlter = accountGateway
				.findById(categoryIdToFindInsideVOAlter);

		Long userIdCategory = categoryVOOutput.getUser().getKey();
		Long userIdCategoryInsideVOAlter = categoryVOOutputInTheSubCategoryToAlter.getUser().getKey();

		if (userId != userIdCategory || userId != userIdCategoryInsideVOAlter) {
			throw new UserDoesNotTheSameOfTheEntity("The user of this account is not the same of the current user");
		}

		creditCardVOUpdateInput.setId(idCreditCard);

		creditCardGateway.updateCreditCard(creditCardVOUpdateInput);
	}
}