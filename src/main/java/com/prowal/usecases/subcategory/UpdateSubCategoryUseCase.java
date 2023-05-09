package com.prowal.usecases.subcategory;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.prowal.entities.category.gateway.CategoryGateway;
import com.prowal.entities.subcategory.gateway.SubCategoryGateway;
import com.prowal.infrastructure.config.db.schema.user.UserSchema;
import com.prowal.infrastructure.config.exceptions.UserDoesNotTheSameOfTheEntity;
import com.prowal.vos.v1.input.subcategory.SubCategoryVOUpdateInput;
import com.prowal.vos.v1.output.category.CategoryVOOutput;
import com.prowal.vos.v1.output.subcategory.SubCategoryVOOutput;

@Service
public class UpdateSubCategoryUseCase {

	private final SubCategoryGateway subCategoryGateway;
	private CategoryGateway categoryGateway;

	public UpdateSubCategoryUseCase(SubCategoryGateway subCategoryGateway, CategoryGateway categoryGateway) {
		this.subCategoryGateway = subCategoryGateway;
		this.categoryGateway = categoryGateway;
	}

	public void execute(SubCategoryVOUpdateInput subCategoryVOUpdateInput, Long idSubCategory) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		UserSchema userDetails = (UserSchema) authentication.getPrincipal();

		Long userId = userDetails.getId();

		SubCategoryVOOutput subCategory = subCategoryGateway.findById(idSubCategory);
		
		Long accountIdToFind = subCategory.getCategory().getKey();
		Long accountIdToFindInsideVOAlter = subCategoryVOUpdateInput.getCategory().getId();
		
		CategoryVOOutput accountVOOutput = categoryGateway.findById(accountIdToFind);
		CategoryVOOutput accountVOOutputInTheSubCategoryToAlter = categoryGateway.findById(accountIdToFindInsideVOAlter);

		Long userIdAccount = accountVOOutput.getUser().getKey();
		Long userIdAccountInsideVOAlter = accountVOOutputInTheSubCategoryToAlter.getUser().getKey();
		
		if (userId != userIdAccount || userId != userIdAccountInsideVOAlter) {
			throw new UserDoesNotTheSameOfTheEntity("The user of this category is not the same of the current user");
		}

		subCategoryVOUpdateInput.setId(idSubCategory);
		
		subCategoryGateway.updateSubCategory(subCategoryVOUpdateInput);
	}
}
