package com.prowal.infrastructure.subcategory.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.prowal.usecases.subcategory.GetSubCategoryByIdUseCase;
import com.prowal.vos.v1.output.subcategory.SubCategoryVOOutput;

@RestController
public class GetSubCategoryByIdController {

	private final GetSubCategoryByIdUseCase getSubCategoryByIdUseCase;

	public GetSubCategoryByIdController(GetSubCategoryByIdUseCase getSubCategoryByIdUseCase) {
		this.getSubCategoryByIdUseCase = getSubCategoryByIdUseCase;
	}

	@GetMapping(path = "api/subcategories/{idSubCategory}")
	public ResponseEntity<SubCategoryVOOutput> getSubCategoryById(
			@PathVariable(name = "idSubCategory") Long idSubCategory) {
		SubCategoryVOOutput subCategoryVOOutput = getSubCategoryByIdUseCase.execute(idSubCategory);

		return ResponseEntity.status(HttpStatus.OK).body(subCategoryVOOutput);
	}
}
