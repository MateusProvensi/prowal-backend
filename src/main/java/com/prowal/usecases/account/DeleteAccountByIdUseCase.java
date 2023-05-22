package com.prowal.usecases.account;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.prowal.entities.account.gateway.AccountGateway;
import com.prowal.infrastructure.config.db.schema.user.UserSchema;
import com.prowal.usecases.belongsValidation.AccountValidation;

@Service
public class DeleteAccountByIdUseCase {

	private final AccountGateway accountGateway;
	private final AccountValidation accountValidation;

	public DeleteAccountByIdUseCase(AccountGateway accountGateway, AccountValidation accountValidation) {
		this.accountGateway = accountGateway;
		this.accountValidation = accountValidation;
	}

	public void execute(Long idCategory) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		UserSchema userDetails = (UserSchema) authentication.getPrincipal();

		accountValidation.verifyIfTheUserEntityIsTheSameOfTheCurrentUser(userDetails, idCategory);

		accountGateway.deleteAccount(idCategory);
	}
}
