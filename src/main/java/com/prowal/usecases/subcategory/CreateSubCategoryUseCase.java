package com.prowal.usecases.subcategory;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.prowal.entities.subcategory.gateway.SubCategoryGateway;
import com.prowal.infrastructure.config.db.schema.user.UserSchema;
import com.prowal.usecases.belongsValidation.CategoryValidation;
import com.prowal.vos.v1.input.subcategory.SubCategoryVOCreateInput;

@Service
public class CreateSubCategoryUseCase {

	private final SubCategoryGateway subCategoryGateway;
	private CategoryValidation categoryValidation;

	public CreateSubCategoryUseCase(SubCategoryGateway subCategoryGateway, CategoryValidation categoryValidation) {
		super();
		this.subCategoryGateway = subCategoryGateway;
		this.categoryValidation = categoryValidation;
	}

	public void execute(SubCategoryVOCreateInput subCategoryVOCreateInput) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		UserSchema userDetails = (UserSchema) authentication.getPrincipal();

		Long categoryId = subCategoryVOCreateInput.getCategory().getId();

		categoryValidation.verifyIfTheUserEntityIsTheSameOfTheCurrentUser(userDetails, categoryId);

		subCategoryGateway.createSubCategory(subCategoryVOCreateInput);
	}
}
