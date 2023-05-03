package com.prowal.infrastructure.subcategory.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.prowal.usecases.subcategory.DeleteSubCategoryByIdUseCase;

@RestController
public class DeleteSubCategoryByIdController {

	private final DeleteSubCategoryByIdUseCase deleteCategoryIdUseCase;

	public DeleteSubCategoryByIdController(DeleteSubCategoryByIdUseCase deleteCategoryIdUseCase) {
		this.deleteCategoryIdUseCase = deleteCategoryIdUseCase;
	}

	@DeleteMapping(path = "api/subcategories/{idSubCategory}")
	public ResponseEntity<Void> deleteCategory(@PathVariable(name = "idSubCategory") Long id) {
		deleteCategoryIdUseCase.execute(id);
		
		return ResponseEntity.status(HttpStatus.OK).build();
	}
}
