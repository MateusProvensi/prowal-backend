package com.prowal.usecases.subcategory;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.prowal.entities.category.gateway.CategoryGateway;
import com.prowal.entities.subcategory.gateway.SubCategoryGateway;
import com.prowal.infrastructure.config.db.schema.user.UserSchema;
import com.prowal.infrastructure.config.exceptions.UserDoesNotTheSameOfTheEntity;
import com.prowal.vos.v1.output.category.CategoryVOOutput;
import com.prowal.vos.v1.output.subcategory.SubCategoryVOOutput;

@Service
public class GetSubCategoryByIdUseCase {

	private final SubCategoryGateway subCategoryGateway;
	private CategoryGateway categoryGateway;

	public GetSubCategoryByIdUseCase(SubCategoryGateway subCategoryGateway, CategoryGateway categoryGateway) {
		this.subCategoryGateway = subCategoryGateway;
		this.categoryGateway = categoryGateway;
	}

	public SubCategoryVOOutput execute(Long idSubCategory) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		UserSchema userDetails = (UserSchema) authentication.getPrincipal();

		Long userId = userDetails.getId();

		SubCategoryVOOutput subCategory = subCategoryGateway.findById(idSubCategory);
		
		Long categoryIdToFind = subCategory.getCategory().getKey();
		
		CategoryVOOutput categoryVOOutput = categoryGateway.findById(categoryIdToFind);

		Long userIdCategory = categoryVOOutput.getUser().getKey();

		if (userId != userIdCategory) {
			throw new UserDoesNotTheSameOfTheEntity("The user of this category is not the same of the current user");
		}

		return subCategory;
	}
}
