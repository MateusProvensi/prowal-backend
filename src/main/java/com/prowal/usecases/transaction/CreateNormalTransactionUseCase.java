package com.prowal.usecases.transaction;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.prowal.entities.subcategory.gateway.SubCategoryGateway;
import com.prowal.entities.transaction.gateway.TransactionGateway;
import com.prowal.infrastructure.config.db.schema.user.UserSchema;
import com.prowal.infrastructure.config.exceptions.EntityDoesNotTheSameException;
import com.prowal.usecases.belongsValidation.AccountValidation;
import com.prowal.usecases.belongsValidation.CategoryValidation;
import com.prowal.vos.v1.input.transaction.TransactionVOCreateCommonInput;
import com.prowal.vos.v1.output.subcategory.SubCategoryVOOutput;

@Service
public class CreateNormalTransactionUseCase {

	private TransactionGateway transactionGateway;
	private SubCategoryGateway subCategoryGateway;
	private CategoryValidation categoryValidation;
	private AccountValidation accountValidation;

	public CreateNormalTransactionUseCase(
			TransactionGateway transactionGateway,
			SubCategoryGateway subCategoryGateway,
			CategoryValidation categoryValidation,
			AccountValidation accountValidation) {
		super();
		this.transactionGateway = transactionGateway;
		this.subCategoryGateway = subCategoryGateway;
		this.categoryValidation = categoryValidation;
		this.accountValidation = accountValidation;
	}

	public void execute(TransactionVOCreateCommonInput transactionVOCreateInput) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		UserSchema userDetails = (UserSchema) authentication.getPrincipal();

		Long primaryAccountId = transactionVOCreateInput.getPrimaryAccount().getId();
		accountValidation.verifyIfTheUserEntityIsTheSameOfTheCurrentUser(userDetails, primaryAccountId);

		Long categoryId = transactionVOCreateInput.getCategory().getId();
		categoryValidation.verifyIfTheUserEntityIsTheSameOfTheCurrentUser(userDetails, categoryId);

		if (transactionVOCreateInput.getSubCategory() != null) {
			SubCategoryVOOutput subcategoryVO = subCategoryGateway
					.findById(transactionVOCreateInput.getSubCategory().getId());

			if (categoryId != subcategoryVO.getCategory().getKey()) {
				throw new EntityDoesNotTheSameException(
						"The category from this subcategory does not the same from the informed category");
			}
		}

		transactionGateway.createCommonTransaction(transactionVOCreateInput);
	}
}
