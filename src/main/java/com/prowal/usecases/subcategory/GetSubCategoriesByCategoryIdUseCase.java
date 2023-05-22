package com.prowal.usecases.subcategory;

import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.prowal.entities.subcategory.gateway.SubCategoryGateway;
import com.prowal.infrastructure.config.db.schema.user.UserSchema;
import com.prowal.infrastructure.config.exceptions.EntityExistsException;
import com.prowal.usecases.belongsValidation.CategoryValidation;
import com.prowal.vos.v1.output.subcategory.SubCategoryVOOutput;

@Service
public class GetSubCategoriesByCategoryIdUseCase {

	private final SubCategoryGateway subCategoryGateway;
	private CategoryValidation categoryValidation;

	public GetSubCategoriesByCategoryIdUseCase(
			SubCategoryGateway subCategoryGateway,
			CategoryValidation categoryValidation) {
		super();
		this.subCategoryGateway = subCategoryGateway;
		this.categoryValidation = categoryValidation;
	}

	public List<SubCategoryVOOutput> execute(Long categoryId) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		UserSchema userDetails = (UserSchema) authentication.getPrincipal();

		categoryValidation.verifyIfTheUserEntityIsTheSameOfTheCurrentUser(userDetails, categoryId);

		List<SubCategoryVOOutput> subcategories = subCategoryGateway.findByCategoryId(categoryId);

		if (subcategories.isEmpty()) {
			throw new EntityExistsException("No subcategory exists that has a category with ID = " + categoryId + ".");
		}

		return subcategories;
	}
}
