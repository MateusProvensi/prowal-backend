package com.prowal.usecases.transaction;

import java.time.Instant;
import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.prowal.entities.transaction.gateway.TransactionGateway;
import com.prowal.infrastructure.config.db.schema.user.UserSchema;
import com.prowal.usecases.belongsValidation.AccountValidation;
import com.prowal.vos.v1.output.transaction.TransactionVoOutput;

import core.FunctionsDateUtils;

@Service
public class GetTransacitionsByPeriodAndAccountUseCase {

	private final TransactionGateway transactionGateway;
	private final AccountValidation accountValidation;
	
	public GetTransacitionsByPeriodAndAccountUseCase(
			TransactionGateway transactionGateway,
			AccountValidation accountValidation) {
		super();
		this.transactionGateway = transactionGateway;
		this.accountValidation = accountValidation;
	}
	
	public List<TransactionVoOutput> execute(Instant initialDate, Instant finalDate, Long idAccount) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		UserSchema userDetails = (UserSchema) authentication.getPrincipal();
		
		accountValidation.verifyIfTheUserEntityIsTheSameOfTheCurrentUser(userDetails, idAccount);

		initialDate = initialDate == null ? FunctionsDateUtils.currentMonthStartDay() : initialDate;
		finalDate = finalDate == null ? FunctionsDateUtils.currentMonthFinalDay() : finalDate;
		
		return transactionGateway.findByPeriodAndAccount(initialDate, finalDate, idAccount);
	}
}
