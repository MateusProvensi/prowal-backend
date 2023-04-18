package com.prowal.infrastructure.category.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.prowal.usecases.category.CreateCategoryUseCase;
import com.prowal.vos.v1.input.category.CategoryVOCreateInput;

import jakarta.validation.Valid;

@RestController
public class CreateCategoryController {

	CreateCategoryUseCase createCategoryUseCase;
	
	public CreateCategoryController(CreateCategoryUseCase createCategoryUseCase) {
		this.createCategoryUseCase = createCategoryUseCase;
	}
	
	@PostMapping(path = "api/categories")
	public ResponseEntity<Void> create(@RequestBody @Valid CategoryVOCreateInput categoryInput) {
		createCategoryUseCase.execute(categoryInput);
		
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}
}
