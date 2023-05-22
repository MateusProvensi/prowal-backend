package com.prowal.usecases.belongsValidation;

import org.springframework.stereotype.Service;

import com.prowal.entities.subcategory.gateway.SubCategoryGateway;
import com.prowal.infrastructure.config.db.schema.user.UserSchema;
import com.prowal.infrastructure.config.exceptions.UserDoesNotTheSameOfTheEntity;
import com.prowal.vos.v1.output.subcategory.SubCategoryVOOutput;

@Service
public class SubCategoryValidation {

	private SubCategoryGateway subCategoryGateway;

	public SubCategoryValidation(SubCategoryGateway subCategoryGateway) {
		super();
		this.subCategoryGateway = subCategoryGateway;
	}

	public void verifyIfTheUserEntityIsTheSameOfTheCurrentUser(UserSchema currentUser, Long idSubCategory) {
		SubCategoryVOOutput subCategoryVOOutput = subCategoryGateway.findById(idSubCategory);
		Long userId = currentUser.getId();

		if (subCategoryVOOutput.getCategory().getUser().getKey() != userId) {
			throw new UserDoesNotTheSameOfTheEntity("The user of this subCategory is not the same of the current user");
		}
	}
}
