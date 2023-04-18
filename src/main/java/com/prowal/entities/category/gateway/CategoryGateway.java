package com.prowal.entities.category.gateway;

import java.util.List;

import com.prowal.vos.v1.input.category.CategoryVOCreateInput;
import com.prowal.vos.v1.input.category.CategoryVOUpdateInput;
import com.prowal.vos.v1.output.category.CategoryVOOutput;

public interface CategoryGateway {
	CategoryVOOutput findById(Long id);

	List<CategoryVOOutput> findByUserId(Long userId);
	
	void createCategory(CategoryVOCreateInput categoryGeneralVO);
	
	void updateCategory(CategoryVOUpdateInput categoryGeneralVO);
	
	void deleteCategory(Long id);
}
