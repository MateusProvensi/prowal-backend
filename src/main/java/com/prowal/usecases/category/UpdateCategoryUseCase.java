package com.prowal.usecases.category;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.prowal.entities.category.gateway.CategoryGateway;
import com.prowal.infrastructure.config.db.schema.user.UserSchema;
import com.prowal.usecases.belongsValidation.CategoryValidation;
import com.prowal.vos.v1.input.category.CategoryVOUpdateInput;

@Service
public class UpdateCategoryUseCase {
	private CategoryGateway categoryGateway;
	private CategoryValidation categoryValidation;

	public UpdateCategoryUseCase(CategoryGateway categoryGateway, CategoryValidation categoryValidation) {
		this.categoryGateway = categoryGateway;
		this.categoryValidation = categoryValidation;
	}

	public void execute(CategoryVOUpdateInput categoryVOUpdateInput, Long idCategory) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		UserSchema userDetails = (UserSchema) authentication.getPrincipal();

		categoryValidation.verifyIfTheUserEntityIsTheSameOfTheCurrentUser(userDetails, idCategory);

		categoryVOUpdateInput.setId(idCategory);

		categoryGateway.updateCategory(categoryVOUpdateInput);
	}
}
