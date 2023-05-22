package com.prowal.usecases.category;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.prowal.entities.category.gateway.CategoryGateway;
import com.prowal.infrastructure.config.db.schema.user.UserSchema;
import com.prowal.usecases.belongsValidation.CategoryValidation;

@Service
public class DeleteCategoryByIdUseCase {

	private CategoryGateway categoryGateway;
	private CategoryValidation categoryValidation;

	public DeleteCategoryByIdUseCase(CategoryGateway categoryGateway, CategoryValidation categoryValidation) {
		this.categoryGateway = categoryGateway;
		this.categoryValidation = categoryValidation;
	}

	public void execute(Long idCategory) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		UserSchema userDetails = (UserSchema) authentication.getPrincipal();

		categoryValidation.verifyIfTheUserEntityIsTheSameOfTheCurrentUser(userDetails, idCategory);

		categoryGateway.deleteCategory(idCategory);
	}
}
