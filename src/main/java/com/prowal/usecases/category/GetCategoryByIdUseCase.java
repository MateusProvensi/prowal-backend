package com.prowal.usecases.category;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.prowal.entities.category.gateway.CategoryGateway;
import com.prowal.infrastructure.config.db.schema.user.UserSchema;
import com.prowal.usecases.belongsValidation.CategoryValidation;
import com.prowal.vos.v1.output.category.CategoryVOOutput;

@Service
public class GetCategoryByIdUseCase {

	private CategoryGateway categoryGateway;
	private CategoryValidation categoryValidation;

	public GetCategoryByIdUseCase(CategoryGateway categoryGateway, CategoryValidation categoryValidation) {
		this.categoryGateway = categoryGateway;
		this.categoryValidation = categoryValidation;
	}

	public CategoryVOOutput execute(Long idCategory) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		UserSchema userDetails = (UserSchema) authentication.getPrincipal();

		categoryValidation.verifyIfTheUserEntityIsTheSameOfTheCurrentUser(userDetails, idCategory);

		CategoryVOOutput categoryVOOutput = categoryGateway.findById(idCategory);

		return categoryVOOutput;
	}
}
