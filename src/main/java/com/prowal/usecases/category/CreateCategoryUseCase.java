package com.prowal.usecases.category;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.prowal.entities.category.gateway.CategoryGateway;
import com.prowal.infrastructure.config.db.schema.user.UserSchema;
import com.prowal.vos.v1.input.category.CategoryVOCreateInput;
import com.prowal.vos.v1.input.ids.UserIdInput;

@Service
public class CreateCategoryUseCase {

	private CategoryGateway categoryGateway;

	public CreateCategoryUseCase(CategoryGateway categoryGateway) {
		this.categoryGateway = categoryGateway;
	}

	public void execute(CategoryVOCreateInput categoryVOInput) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserSchema userDetails = (UserSchema) authentication.getPrincipal();
        
        UserIdInput userInput = new UserIdInput(userDetails.getId());
        categoryVOInput.setUser(userInput);
        
        categoryGateway.createCategory(categoryVOInput);
	}
}
