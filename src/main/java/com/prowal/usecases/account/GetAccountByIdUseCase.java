package com.prowal.usecases.account;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.prowal.entities.account.gateway.AccountGateway;
import com.prowal.infrastructure.config.db.schema.user.UserSchema;
import com.prowal.infrastructure.config.exceptions.UserDoesNotTheSameOfTheEntity;
import com.prowal.vos.v1.output.account.AccountVOOutput;

@Service
public class GetAccountByIdUseCase {

	private final AccountGateway accountGateway;

	public GetAccountByIdUseCase(AccountGateway accountGateway) {
		this.accountGateway = accountGateway;
	}
	
	public AccountVOOutput execute(Long id) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		UserSchema userDetails = (UserSchema) authentication.getPrincipal();

		Long userId = userDetails.getId();

		AccountVOOutput accountVOOutput = accountGateway.findById(id);

		Long userIdAccountToDelete = accountVOOutput.getUser().getKey();

		if (userId != userIdAccountToDelete) {
			throw new UserDoesNotTheSameOfTheEntity("The user does not the same of the entity");
		}

		return accountVOOutput;
	}
}
