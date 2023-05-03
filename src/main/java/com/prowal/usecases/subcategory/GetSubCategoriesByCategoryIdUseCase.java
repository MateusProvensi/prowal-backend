package com.prowal.usecases.subcategory;

import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.prowal.entities.category.gateway.CategoryGateway;
import com.prowal.entities.subcategory.gateway.SubCategoryGateway;
import com.prowal.infrastructure.config.db.schema.user.UserSchema;
import com.prowal.infrastructure.config.exceptions.EntityExistsException;
import com.prowal.infrastructure.config.exceptions.UserDoesNotTheSameOfTheEntity;
import com.prowal.vos.v1.output.category.CategoryVOOutput;
import com.prowal.vos.v1.output.subcategory.SubCategoryVOOutput;

@Service
public class GetSubCategoriesByCategoryIdUseCase {

	private final SubCategoryGateway subCategoryGateway;
	private CategoryGateway categoryGateway;

	public GetSubCategoriesByCategoryIdUseCase(SubCategoryGateway subCategoryGateway, CategoryGateway categoryGateway) {
		this.subCategoryGateway = subCategoryGateway;
		this.categoryGateway = categoryGateway;
	}

	public List<SubCategoryVOOutput> execute(Long categoryId) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		UserSchema userDetails = (UserSchema) authentication.getPrincipal();

		Long userId = userDetails.getId();

		CategoryVOOutput categoryVOOutput = categoryGateway.findById(categoryId);

		Long userIdCategory = categoryVOOutput.getUser().getKey();

		if (userId != userIdCategory) {
			throw new UserDoesNotTheSameOfTheEntity("The user of this category is not the same of the current user");
		}

		List<SubCategoryVOOutput> subcategories = subCategoryGateway.findByCategoryId(categoryId);

		if (subcategories.isEmpty()) {
			throw new EntityExistsException("No subcategory exists that has a category with ID = " + categoryId + ".");
		}

		return subcategories;
	}
}
