package com.prowal.usecases.category;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.prowal.entities.category.gateway.CategoryGateway;
import com.prowal.infrastructure.config.db.schema.user.UserSchema;
import com.prowal.infrastructure.config.exceptions.UserDoesNotTheSameOfTheEntity;
import com.prowal.vos.v1.output.category.CategoryVOOutput;

@Service
public class DeleteCategoryByIdUseCase {

	private CategoryGateway categoryGateway;

	public DeleteCategoryByIdUseCase(CategoryGateway categoryGateway) {
		this.categoryGateway = categoryGateway;
	}
	
	public void execute(Long id) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		UserSchema userDetails = (UserSchema) authentication.getPrincipal();

		Long userId = userDetails.getId();

		CategoryVOOutput categoryVOOutput = categoryGateway.findById(id);

		Long userIdCategoryChange = categoryVOOutput.getUser().getKey();

		if (userId != userIdCategoryChange) {
			throw new UserDoesNotTheSameOfTheEntity("The user does not the same of the entity");
		}
		
		categoryGateway.deleteCategory(categoryVOOutput.getKey());
	}
}
