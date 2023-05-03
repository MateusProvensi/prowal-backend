package com.prowal.entities.subcategory.gateway;

import java.util.List;

import com.prowal.vos.v1.input.subcategory.SubCategoryVOCreateInput;
import com.prowal.vos.v1.input.subcategory.SubCategoryVOUpdateInput;
import com.prowal.vos.v1.output.subcategory.SubCategoryVOOutput;

public interface SubCategoryGateway {
	SubCategoryVOOutput findById(Long id);

	List<SubCategoryVOOutput> findByCategoryId(Long categoryId);
	
	void createSubCategory(SubCategoryVOCreateInput subCategoryVOCreateInput);
	
	void updateSubCategory(SubCategoryVOUpdateInput subCategoryVOUpdateInput);
	
	void deleteSubCategory(Long id);
}
