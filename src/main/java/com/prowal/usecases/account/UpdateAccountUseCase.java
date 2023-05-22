package com.prowal.usecases.account;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.prowal.entities.account.gateway.AccountGateway;
import com.prowal.infrastructure.config.db.schema.user.UserSchema;
import com.prowal.usecases.belongsValidation.AccountValidation;
import com.prowal.vos.v1.input.account.AccountVOUpdateInput;

@Service
public class UpdateAccountUseCase {

	private final AccountGateway accountGateway;
	private final AccountValidation accountValidation;

	public UpdateAccountUseCase(AccountGateway accountGateway, AccountValidation accountValidation) {
		super();
		this.accountGateway = accountGateway;
		this.accountValidation = accountValidation;
	}

	public void execute(AccountVOUpdateInput accountVOUpdateInput, Long idAccount) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		UserSchema userDetails = (UserSchema) authentication.getPrincipal();

		accountValidation.verifyIfTheUserEntityIsTheSameOfTheCurrentUser(userDetails, idAccount);

		accountVOUpdateInput.setId(idAccount);

		accountGateway.updateAccount(accountVOUpdateInput);
	}
}
