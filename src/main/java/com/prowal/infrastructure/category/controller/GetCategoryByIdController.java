package com.prowal.infrastructure.category.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.prowal.usecases.category.GetCategoryByIdUseCase;
import com.prowal.vos.v1.output.category.CategoryVOOutput;

@RestController
public class GetCategoryByIdController {

	GetCategoryByIdUseCase getCategoryByIdUseCase;

	public GetCategoryByIdController(GetCategoryByIdUseCase getCategoryByIdUseCase) {
		this.getCategoryByIdUseCase = getCategoryByIdUseCase;
	}

	@GetMapping("api/v1/categories/{idCategory}")
	public ResponseEntity<CategoryVOOutput> getCategoryById(@PathVariable(name = "idCategory") Long idCategory) {
		CategoryVOOutput categoryVOOutput = getCategoryByIdUseCase.execute(idCategory);

		return ResponseEntity.status(HttpStatus.OK).body(categoryVOOutput);
	}
}
