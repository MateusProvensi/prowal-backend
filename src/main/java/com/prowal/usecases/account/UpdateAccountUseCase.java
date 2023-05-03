package com.prowal.usecases.account;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.prowal.entities.account.gateway.AccountGateway;
import com.prowal.infrastructure.config.db.schema.user.UserSchema;
import com.prowal.infrastructure.config.exceptions.UserDoesNotTheSameOfTheEntity;
import com.prowal.vos.v1.input.account.AccountVOUpdateInput;
import com.prowal.vos.v1.input.ids.UserIdInput;
import com.prowal.vos.v1.output.account.AccountVOOutput;

@Service
public class UpdateAccountUseCase {

	private final AccountGateway accountGateway;

	public UpdateAccountUseCase(AccountGateway accountGateway) {
		this.accountGateway = accountGateway;
	}
	
	public void execute(AccountVOUpdateInput accountVOUpdateInput, Long idAccount) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		UserSchema userDetails = (UserSchema) authentication.getPrincipal();
		
		Long userId = userDetails.getId();
		
		AccountVOOutput accountVOOutput = accountGateway.findById(idAccount);
		
		Long userIdAccountChange = accountVOOutput.getUser().getKey();
		
		if (userId != userIdAccountChange) {
			throw new UserDoesNotTheSameOfTheEntity("The user does not the same of the entity");
		}
		
		accountVOUpdateInput.setId(accountVOOutput.getKey());
		accountVOUpdateInput.setUser(new UserIdInput(userIdAccountChange));
		
		accountGateway.updateAccount(accountVOUpdateInput);
	}
}
