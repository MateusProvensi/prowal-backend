package com.prowal.infrastructure.subcategory.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.prowal.usecases.subcategory.CreateSubCategoryUseCase;
import com.prowal.vos.v1.input.subcategory.SubCategoryVOCreateInput;

import jakarta.validation.Valid;

@RestController
public class CreateSubCategoryController {

	private final CreateSubCategoryUseCase createSubCategoryUseCase;

	public CreateSubCategoryController(CreateSubCategoryUseCase createSubCategoryUseCase) {
		this.createSubCategoryUseCase = createSubCategoryUseCase;
	}

	@PostMapping(path = "api/v1/subcategories")
	public ResponseEntity<Void> createSubCategory(@RequestBody @Valid SubCategoryVOCreateInput categoryVOCreateInput) {
		createSubCategoryUseCase.execute(categoryVOCreateInput);

		return ResponseEntity.status(HttpStatus.CREATED).build();
	}

}
