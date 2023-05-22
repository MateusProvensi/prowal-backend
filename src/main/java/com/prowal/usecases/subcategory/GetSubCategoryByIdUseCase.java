package com.prowal.usecases.subcategory;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.prowal.entities.subcategory.gateway.SubCategoryGateway;
import com.prowal.infrastructure.config.db.schema.user.UserSchema;
import com.prowal.usecases.belongsValidation.SubCategoryValidation;
import com.prowal.vos.v1.output.subcategory.SubCategoryVOOutput;

@Service
public class GetSubCategoryByIdUseCase {

	private final SubCategoryGateway subCategoryGateway;
	private final SubCategoryValidation subCategoryValidation;

	public GetSubCategoryByIdUseCase(
			SubCategoryGateway subCategoryGateway,
			SubCategoryValidation subCategoryValidation) {
		super();
		this.subCategoryGateway = subCategoryGateway;
		this.subCategoryValidation = subCategoryValidation;
	}

	public SubCategoryVOOutput execute(Long idSubCategory) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		UserSchema userDetails = (UserSchema) authentication.getPrincipal();

		subCategoryValidation.verifyIfTheUserEntityIsTheSameOfTheCurrentUser(userDetails, idSubCategory);

		SubCategoryVOOutput subCategory = subCategoryGateway.findById(idSubCategory);

		return subCategory;
	}
}
