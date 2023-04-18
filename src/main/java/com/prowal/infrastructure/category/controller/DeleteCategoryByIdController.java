package com.prowal.infrastructure.category.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.prowal.usecases.category.DeleteCategoryByIdUseCase;

@RestController
public class DeleteCategoryByIdController {

	private DeleteCategoryByIdUseCase deleteCategoryByIdUseCase;

	public DeleteCategoryByIdController(DeleteCategoryByIdUseCase deleteCategoryByIdUseCase) {
		this.deleteCategoryByIdUseCase = deleteCategoryByIdUseCase;
	}

	@DeleteMapping(path = "api/categories/{idCategory}")
	public ResponseEntity<Void> deleteCategory(@PathVariable(name = "idCategory") Long id) {
		deleteCategoryByIdUseCase.execute(id);
		
		return ResponseEntity.status(HttpStatus.OK).build();
	}
}
