package com.prowal.usecases.belongsValidation;

import org.springframework.stereotype.Service;

import com.prowal.entities.account.gateway.AccountGateway;
import com.prowal.infrastructure.config.db.schema.user.UserSchema;
import com.prowal.infrastructure.config.exceptions.UserDoesNotTheSameOfTheEntity;
import com.prowal.vos.v1.output.account.AccountVOOutput;

@Service
public class AccountValidation {

	private AccountGateway accountGateway;

	public AccountValidation(AccountGateway accountGateway) {
		super();
		this.accountGateway = accountGateway;
	}
	
	public void verifyIfTheUserEntityIsTheSameOfTheCurrentUser(UserSchema currentUser, Long idAcount) {
		AccountVOOutput account = accountGateway.findById(idAcount);
		Long userId = currentUser.getId();
		
		if (account.getUser().getKey() != userId) {
			throw new UserDoesNotTheSameOfTheEntity("The user of this account is not the same of the current user");
		}
	}
}
