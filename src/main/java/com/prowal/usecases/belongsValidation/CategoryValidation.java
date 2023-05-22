package com.prowal.usecases.belongsValidation;

import org.springframework.stereotype.Service;

import com.prowal.entities.category.gateway.CategoryGateway;
import com.prowal.infrastructure.config.db.schema.user.UserSchema;
import com.prowal.infrastructure.config.exceptions.UserDoesNotTheSameOfTheEntity;
import com.prowal.vos.v1.output.category.CategoryVOOutput;

@Service
public class CategoryValidation {

	private CategoryGateway categoryGateway;

	public CategoryValidation(CategoryGateway categoryGateway) {
		super();
		this.categoryGateway = categoryGateway;
	}

	public void verifyIfTheUserEntityIsTheSameOfTheCurrentUser(UserSchema currentUser, Long idCategory) {
		CategoryVOOutput category = categoryGateway.findById(idCategory);
		Long userId = currentUser.getId();

		if (category.getUser().getKey() != userId) {
			throw new UserDoesNotTheSameOfTheEntity("The user of this category is not the same of the current user");
		}
	}
}
