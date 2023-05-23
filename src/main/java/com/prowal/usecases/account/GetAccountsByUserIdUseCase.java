package com.prowal.usecases.account;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.prowal.entities.account.gateway.AccountGateway;
import com.prowal.entities.transaction.gateway.TransactionGateway;
import com.prowal.infrastructure.config.db.schema.user.UserSchema;
import com.prowal.vos.v1.output.account.AccountVOOutput;

@Service
public class GetAccountsByUserIdUseCase {

	private final AccountGateway accountGateway;
	private final TransactionGateway transactionGateway;

	public GetAccountsByUserIdUseCase(AccountGateway accountGateway, TransactionGateway transactionGateway) {
		super();
		this.accountGateway = accountGateway;
		this.transactionGateway = transactionGateway;
	}

	public List<AccountVOOutput> execute() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		UserSchema userDetails = (UserSchema) authentication.getPrincipal();

		Long userId = userDetails.getId();

		List<AccountVOOutput> accountsFromCurrentUser = accountGateway.findByUserId(userId);

		for (AccountVOOutput account : accountsFromCurrentUser) {
			BigDecimal accountBalance = transactionGateway.getCurrentBalanceToAccount(account.getKey());

			account.setBalance(accountBalance);
		}

		return accountsFromCurrentUser;
	}
}
