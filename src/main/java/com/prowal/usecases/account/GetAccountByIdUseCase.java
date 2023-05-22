package com.prowal.usecases.account;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.prowal.entities.account.gateway.AccountGateway;
import com.prowal.infrastructure.config.db.schema.user.UserSchema;
import com.prowal.usecases.belongsValidation.AccountValidation;
import com.prowal.vos.v1.output.account.AccountVOOutput;

@Service
public class GetAccountByIdUseCase {

	private final AccountGateway accountGateway;
	private final AccountValidation accountValidation;

	public GetAccountByIdUseCase(AccountGateway accountGateway, AccountValidation accountValidation) {
		this.accountGateway = accountGateway;
		this.accountValidation = accountValidation;
	}
	
	public AccountVOOutput execute(Long idAccount) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		UserSchema userDetails = (UserSchema) authentication.getPrincipal();

		accountValidation.verifyIfTheUserEntityIsTheSameOfTheCurrentUser(userDetails, idAccount);
		
		AccountVOOutput accountVOOutput = accountGateway.findById(idAccount);

		return accountVOOutput;
	}
}
