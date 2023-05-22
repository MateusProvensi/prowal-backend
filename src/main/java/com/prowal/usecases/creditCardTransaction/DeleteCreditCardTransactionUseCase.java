package com.prowal.usecases.creditCardTransaction;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.prowal.entities.creditCardTransaction.gateway.CreditCardTransactionGateway;
import com.prowal.infrastructure.config.db.schema.user.UserSchema;
import com.prowal.usecases.belongsValidation.CreditCardValidation;
import com.prowal.vos.v1.output.creditCardTransaction.CreditCardTransactionVOOutput;

@Service
public class DeleteCreditCardTransactionUseCase {

	private CreditCardTransactionGateway creditCardTransactionGateway;
	private CreditCardValidation creditCardValidation;

	public DeleteCreditCardTransactionUseCase(
			CreditCardTransactionGateway creditCardTransactionGateway,
			CreditCardValidation creditCardValidation) {
		super();
		this.creditCardTransactionGateway = creditCardTransactionGateway;
		this.creditCardValidation = creditCardValidation;
	}

	public void execute(Long idCreditCardTransaction) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		UserSchema userDetails = (UserSchema) authentication.getPrincipal();

		CreditCardTransactionVOOutput creditCardTransactionVO = creditCardTransactionGateway
				.findById(idCreditCardTransaction);

		Long creditCardId = creditCardTransactionVO.getCreditCard().getKey();

		creditCardValidation.verifyIfTheUserEntityIsTheSameOfTheCurrentUser(userDetails, creditCardId);

		creditCardTransactionGateway.deleteCreditCardTransaction(idCreditCardTransaction);
	}
}
