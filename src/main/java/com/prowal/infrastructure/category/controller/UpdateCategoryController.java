package com.prowal.infrastructure.category.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.prowal.usecases.category.UpdateCategoryUseCase;
import com.prowal.vos.v1.input.category.CategoryVOUpdateInput;

import jakarta.validation.Valid;

@RestController
public class UpdateCategoryController {
	UpdateCategoryUseCase updateCategoryUseCase;

	public UpdateCategoryController(UpdateCategoryUseCase updateCategoryUseCase) {
		this.updateCategoryUseCase = updateCategoryUseCase;
	}

	@PutMapping(path = "api/categories/{idCategory}")
	public ResponseEntity<Void> update(
			@PathVariable(name = "idCategory") Long idCategory,
			@RequestBody @Valid CategoryVOUpdateInput categoryInput) {
		updateCategoryUseCase.execute(categoryInput, idCategory);

		return ResponseEntity.status(HttpStatus.OK).build();
	}
}
