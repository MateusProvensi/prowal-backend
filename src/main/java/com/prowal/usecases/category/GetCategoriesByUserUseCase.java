package com.prowal.usecases.category;

import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.prowal.entities.category.gateway.CategoryGateway;
import com.prowal.infrastructure.config.db.schema.user.UserSchema;
import com.prowal.vos.v1.output.category.CategoryVOOutput;

@Service
public class GetCategoriesByUserUseCase {

	CategoryGateway categoryGateway;

	public GetCategoriesByUserUseCase(CategoryGateway categoryGateway) {
		this.categoryGateway = categoryGateway;
	}

	public List<CategoryVOOutput> execute() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		UserSchema userDetails = (UserSchema) authentication.getPrincipal();

		Long userId = userDetails.getId();

		List<CategoryVOOutput> categoriesFromCurrentUser = categoryGateway.findByUserId(userId);

		return categoriesFromCurrentUser;
	}
}
