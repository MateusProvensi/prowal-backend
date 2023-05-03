package com.prowal.usecases.account;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.prowal.entities.account.gateway.AccountGateway;
import com.prowal.infrastructure.config.db.schema.user.UserSchema;
import com.prowal.vos.v1.input.account.AccountVOCreateInput;
import com.prowal.vos.v1.input.ids.UserIdInput;

@Service
public class CreateAccountUseCase {

	private final AccountGateway accountGateway;

	public CreateAccountUseCase(AccountGateway accountGateway) {
		this.accountGateway = accountGateway;
	}

	public void execute(AccountVOCreateInput accountVOCreateInput) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		UserSchema userDetails = (UserSchema) authentication.getPrincipal();

		UserIdInput userInput = new UserIdInput(userDetails.getId());
		accountVOCreateInput.setUser(userInput);

		accountGateway.createAccount(accountVOCreateInput);
	}

}
