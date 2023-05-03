package com.prowal.infrastructure.subcategory.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.prowal.usecases.subcategory.UpdateSubCategoryUseCase;
import com.prowal.vos.v1.input.subcategory.SubCategoryVOUpdateInput;

import jakarta.validation.Valid;

@RestController
public class UpdateSubCategoryController {

	private final UpdateSubCategoryUseCase updateSubCategoryUseCase;

	public UpdateSubCategoryController(UpdateSubCategoryUseCase updateSubCategoryUseCase) {
		this.updateSubCategoryUseCase = updateSubCategoryUseCase;
	}

	@PutMapping(path = "api/subcategories/{idSubCategory}")
	public ResponseEntity<Void> getSubCategoryById(
			@PathVariable(name = "idSubCategory") Long idSubCategory,
			@RequestBody @Valid SubCategoryVOUpdateInput subCategoryInput) {
		updateSubCategoryUseCase.execute(subCategoryInput, idSubCategory);

		return ResponseEntity.status(HttpStatus.OK).build();
	}
}
