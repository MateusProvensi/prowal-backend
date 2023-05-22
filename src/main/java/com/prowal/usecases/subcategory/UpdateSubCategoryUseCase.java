package com.prowal.usecases.subcategory;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.prowal.entities.subcategory.gateway.SubCategoryGateway;
import com.prowal.infrastructure.config.db.schema.user.UserSchema;
import com.prowal.usecases.belongsValidation.SubCategoryValidation;
import com.prowal.vos.v1.input.subcategory.SubCategoryVOUpdateInput;

@Service
public class UpdateSubCategoryUseCase {

	private final SubCategoryGateway subCategoryGateway;
	private SubCategoryValidation subCategoryValidation;

	public UpdateSubCategoryUseCase(
			SubCategoryGateway subCategoryGateway,
			SubCategoryValidation subCategoryValidation) {
		this.subCategoryGateway = subCategoryGateway;
		this.subCategoryValidation = subCategoryValidation;
	}

	public void execute(SubCategoryVOUpdateInput subCategoryVOUpdateInput, Long idSubCategory) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		UserSchema userDetails = (UserSchema) authentication.getPrincipal();

		subCategoryValidation.verifyIfTheUserEntityIsTheSameOfTheCurrentUser(userDetails, idSubCategory);

		subCategoryVOUpdateInput.setId(idSubCategory);

		subCategoryGateway.updateSubCategory(subCategoryVOUpdateInput);
	}
}
