package com.prowal.usecases.category;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.prowal.entities.category.gateway.CategoryGateway;
import com.prowal.infrastructure.config.db.schema.user.UserSchema;
import com.prowal.infrastructure.config.exceptions.UserDoesNotTheSameOfTheEntity;
import com.prowal.vos.v1.input.category.CategoryVOUpdateInput;
import com.prowal.vos.v1.input.ids.UserIdInput;
import com.prowal.vos.v1.output.category.CategoryVOOutput;

@Service
public class UpdateCategoryUseCase {
	private CategoryGateway categoryGateway;

	public UpdateCategoryUseCase(CategoryGateway categoryGateway) {
		this.categoryGateway = categoryGateway;
	}

	public void execute(CategoryVOUpdateInput categoryVOUpdateInput, Long idCategory) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		UserSchema userDetails = (UserSchema) authentication.getPrincipal();
		
		Long userId = userDetails.getId();
		
		CategoryVOOutput categoryVOOutput = categoryGateway.findById(idCategory);
		
		Long userIdCategoryChange = categoryVOOutput.getUser().getKey();
		
		if (userId != userIdCategoryChange) {
			throw new UserDoesNotTheSameOfTheEntity("The user does not the same of the entity");
		}
		
		categoryVOUpdateInput.setKey(categoryVOOutput.getKey());
		categoryVOUpdateInput.setUser(new UserIdInput(userIdCategoryChange));
		
		categoryGateway.updateCategory(categoryVOUpdateInput);
	}
}
