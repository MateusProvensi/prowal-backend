package com.prowal.usecases.transaction;

import java.time.Instant;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.prowal.entities.creditCard.gateway.CreditCardGateway;
import com.prowal.entities.creditCardTransaction.gateway.CreditCardTransactionGateway;
import com.prowal.entities.subcategory.gateway.SubCategoryGateway;
import com.prowal.entities.transaction.gateway.TransactionGateway;
import com.prowal.infrastructure.config.db.schema.transaction.TypeTransaction;
import com.prowal.infrastructure.config.db.schema.user.UserSchema;
import com.prowal.infrastructure.config.exceptions.EntityDoesNotTheSameException;
import com.prowal.infrastructure.config.exceptions.WrongTypeEnumException;
import com.prowal.usecases.belongsValidation.AccountValidation;
import com.prowal.usecases.belongsValidation.CategoryValidation;
import com.prowal.vos.v1.input.creditCardTransaction.CreditCardTransactionVOCreateInput;
import com.prowal.vos.v1.input.ids.CreditCardTransactionIdInput;
import com.prowal.vos.v1.input.transaction.TransactionVOCreateFromCreditCardInput;
import com.prowal.vos.v1.output.creditCard.CreditCardVOOutput;
import com.prowal.vos.v1.output.creditCardTransaction.CreditCardTransactionVOOutput;
import com.prowal.vos.v1.output.subcategory.SubCategoryVOOutput;

import core.FunctionsDateUtils;

@Service
public class CreateTransactionFromCreditCardUseCase {

	private TransactionGateway transactionGateway;
	private CreditCardTransactionGateway creditCardTransactionGateway;
	private SubCategoryGateway subCategoryGateway;
	private CreditCardGateway creditCardGateway;
	private CategoryValidation categoryValidation;
	private AccountValidation accountValidation;

	public CreateTransactionFromCreditCardUseCase(
			TransactionGateway transactionGateway,
			CreditCardTransactionGateway creditCardTransactionGateway,
			SubCategoryGateway subCategoryGateway,
			CreditCardGateway creditCardGateway,
			CategoryValidation categoryValidation,
			AccountValidation accountValidation) {
		super();
		this.transactionGateway = transactionGateway;
		this.creditCardTransactionGateway = creditCardTransactionGateway;
		this.subCategoryGateway = subCategoryGateway;
		this.creditCardGateway = creditCardGateway;
		this.categoryValidation = categoryValidation;
		this.accountValidation = accountValidation;
	}

	public void execute(TransactionVOCreateFromCreditCardInput transactionVOCreateInput) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		UserSchema userDetails = (UserSchema) authentication.getPrincipal();

		if (transactionVOCreateInput.getType() != TypeTransaction.EXPENSE) {
			throw new WrongTypeEnumException("The informed type is not possible for this operation");
		}

		Long primaryAccountId = transactionVOCreateInput.getPrimaryAccount().getId();
		Long categoryId = transactionVOCreateInput.getCategory().getId();

		categoryValidation.verifyIfTheUserEntityIsTheSameOfTheCurrentUser(userDetails, categoryId);
		accountValidation.verifyIfTheUserEntityIsTheSameOfTheCurrentUser(userDetails, primaryAccountId);

		Long creditCardId = transactionVOCreateInput.getCreditCardTransactionInsert().getCreditCard().getId();
		CreditCardVOOutput creditCardVO = creditCardGateway.findById(creditCardId);

		if (creditCardVO.getAccount().getKey() != primaryAccountId) {
			throw new EntityDoesNotTheSameException(
					"The credit card from this account does not the same from the informed account");
		}

		if (transactionVOCreateInput.getSubCategory() != null) {
			SubCategoryVOOutput subcategoryVO = subCategoryGateway
					.findById(transactionVOCreateInput.getSubCategory().getId());

			if (categoryId != subcategoryVO.getCategory().getKey()) {
				throw new EntityDoesNotTheSameException(
						"The category from this subcategory does not the same from the informed category");
			}
		}

		Integer maxQuantityOfInstallments = transactionVOCreateInput
				.getCreditCardTransactionInsert()
				.getQuantityOfInstallments();

		Instant initialDate = transactionVOCreateInput.getDate();
		CreditCardTransactionVOCreateInput transactionTOCreate = transactionVOCreateInput
				.getCreditCardTransactionInsert();

		for (int i = 1; i < maxQuantityOfInstallments; i++) {
			if (i > 1) {
				Integer quantityMonthToIncrease = i - 1;

				Instant newDateIncreased = FunctionsDateUtils.increaseMonthToDate(initialDate, quantityMonthToIncrease);

				transactionTOCreate.setDate(newDateIncreased);
				transactionVOCreateInput.setDate(newDateIncreased);
			}

			transactionTOCreate.setInstallment(i);

			CreditCardTransactionVOOutput creditCardTransaction = creditCardTransactionGateway
					.createCreditCardTransaction(transactionTOCreate);

			Long creditCardTransactionId = creditCardTransaction.getKey();

			transactionVOCreateInput
					.setCreditCardTransaction(new CreditCardTransactionIdInput(creditCardTransactionId));

			transactionGateway.createTransactionFromCreditCard(transactionVOCreateInput);
		}
	}
}
