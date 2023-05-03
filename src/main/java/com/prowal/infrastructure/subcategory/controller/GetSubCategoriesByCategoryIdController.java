package com.prowal.infrastructure.subcategory.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.prowal.usecases.subcategory.GetSubCategoriesByCategoryIdUseCase;
import com.prowal.vos.v1.output.subcategory.SubCategoryVOOutput;

@RestController
public class GetSubCategoriesByCategoryIdController {

	private final GetSubCategoriesByCategoryIdUseCase getSubCategoriesByCategoryIdUseCase;

	public GetSubCategoriesByCategoryIdController(
			GetSubCategoriesByCategoryIdUseCase getSubCategoriesByCategoryIdUseCase) {
		this.getSubCategoriesByCategoryIdUseCase = getSubCategoriesByCategoryIdUseCase;
	}

	@GetMapping(path = "api/subcategories/category/{idCategory}")
	public ResponseEntity<List<SubCategoryVOOutput>> getSubCategoriesByCategory(
			@PathVariable(name = "idCategory") Long idCategory) {
		List<SubCategoryVOOutput> subcategories = getSubCategoriesByCategoryIdUseCase.execute(idCategory);
		
		return ResponseEntity.status(HttpStatus.OK).body(subcategories);
	}
}
