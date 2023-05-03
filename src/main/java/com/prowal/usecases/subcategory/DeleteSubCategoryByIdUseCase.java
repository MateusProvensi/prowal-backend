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
public class DeleteSubCategoryByIdUseCase {

	private final SubCategoryGateway subCategoryGateway;
	private CategoryGateway categoryGateway;

	public DeleteSubCategoryByIdUseCase(SubCategoryGateway subCategoryGateway, CategoryGateway categoryGateway) {
		this.subCategoryGateway = subCategoryGateway;
		this.categoryGateway = categoryGateway;
	}
	
	public void execute(Long id) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserSchema userDetails = (UserSchema) authentication.getPrincipal();

        Long userId = userDetails.getId();
        
        SubCategoryVOOutput subcategoryToDelete = subCategoryGateway.findById(id);
        
        Long categoryId = subcategoryToDelete.getCategory().getKey();
        
        CategoryVOOutput category = categoryGateway.findById(categoryId);
        
        if (category.getUser().getKey() != userId) {
        	throw new UserDoesNotTheSameOfTheEntity("The user of this category is not the same of the current user"); 
        }
        
        subCategoryGateway.deleteSubCategory(id);
	}
}
