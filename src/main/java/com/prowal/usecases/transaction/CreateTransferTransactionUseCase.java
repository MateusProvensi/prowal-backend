package com.prowal.usecases.transaction;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.prowal.entities.transaction.gateway.TransactionGateway;
import com.prowal.infrastructure.config.db.schema.transaction.TypeTransaction;
import com.prowal.infrastructure.config.db.schema.user.UserSchema;
import com.prowal.infrastructure.config.exceptions.WrongTypeEnumException;
import com.prowal.usecases.belongsValidation.AccountValidation;
import com.prowal.vos.v1.input.transaction.TransactionVOCreateTransferInput;

@Service
public class CreateTransferTransactionUseCase {

	private TransactionGateway transactionGateway;
	private AccountValidation accountValidation;

	public CreateTransferTransactionUseCase(
			TransactionGateway transactionGateway,
			AccountValidation accountValidation) {
		super();
		this.transactionGateway = transactionGateway;
		this.accountValidation = accountValidation;
	}

	public void execute(TransactionVOCreateTransferInput transactionVOCreateTransferInput) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		UserSchema userDetails = (UserSchema) authentication.getPrincipal();

		TypeTransaction tipoTransacao = transactionVOCreateTransferInput.getType();

		if (tipoTransacao != TypeTransaction.TRANSFER) {
			throw new WrongTypeEnumException("The informed type is not possible for this operation");
		}

		Long primaryAccountId = transactionVOCreateTransferInput.getPrimaryAccount().getId();
		accountValidation.verifyIfTheUserEntityIsTheSameOfTheCurrentUser(userDetails, primaryAccountId);

		Long secondaryAccountId = transactionVOCreateTransferInput.getSecondaryAccount().getId();
		accountValidation.verifyIfTheUserEntityIsTheSameOfTheCurrentUser(userDetails, secondaryAccountId);

		transactionGateway.createTransferTransaction(transactionVOCreateTransferInput);
	}
}
