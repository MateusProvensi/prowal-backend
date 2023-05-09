package com.prowal.infrastructure.category.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.prowal.usecases.category.GetCategoriesByUserUseCase;
import com.prowal.vos.v1.output.category.CategoryVOOutput;

@RestController
public class GetCategoriesByUserController {

	private GetCategoriesByUserUseCase getCategoriesByUserUseCase;

	public GetCategoriesByUserController(GetCategoriesByUserUseCase getCategoriesByUserUseCase) {
		this.getCategoriesByUserUseCase = getCategoriesByUserUseCase;
	}
	
	@GetMapping(path = "api/v1/categories/current-user/all")
	public ResponseEntity<List<CategoryVOOutput>> getCategoriesFromCurrentUser() {
		List<CategoryVOOutput> categories = getCategoriesByUserUseCase.execute();
		
		return ResponseEntity.status(HttpStatus.OK).body(categories);
	}
}
