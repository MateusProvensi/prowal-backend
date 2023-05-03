package com.prowal.infrastructure.category.gateway;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Component;

import com.prowal.entities.category.gateway.CategoryGateway;
import com.prowal.infrastructure.config.db.repositories.CategoryRepository;
import com.prowal.infrastructure.config.db.schema.category.CategorySchema;
import com.prowal.infrastructure.config.exceptions.EntityExistsException;
import com.prowal.infrastructure.config.mapper.ModelMapperMaping;
import com.prowal.vos.v1.input.category.CategoryVOCreateInput;
import com.prowal.vos.v1.input.category.CategoryVOUpdateInput;
import com.prowal.vos.v1.output.category.CategoryVOOutput;

@Component
public class CategoryDatabaseGateway implements CategoryGateway {

	private final CategoryRepository categoryRepository;

	public CategoryDatabaseGateway(CategoryRepository categoryRepository) {
		this.categoryRepository = categoryRepository;
	}

	@Override
	public CategoryVOOutput findById(Long id) {
		Optional<CategorySchema> category = categoryRepository.findById(id);

		CategoryVOOutput categoryVOOutput = category
				.map(currentCategory -> ModelMapperMaping.parseObject(currentCategory, CategoryVOOutput.class))
				.orElseThrow(() -> new EntityExistsException("The category with ID = " + id + " does not exists."));

		return categoryVOOutput;
	}

	@Override
	public List<CategoryVOOutput> findByUserId(Long userId) {
		List<CategorySchema> categories = categoryRepository.findByUserId(userId);

		return ModelMapperMaping.parseListObjects(categories, CategoryVOOutput.class);
	}

	@Override
	public void createCategory(CategoryVOCreateInput categoryVOInput) {
		CategorySchema categoryToInsert = ModelMapperMaping.parseObject(categoryVOInput, CategorySchema.class);
		
		categoryRepository.save(categoryToInsert);
	}

	@Override
	public void updateCategory(CategoryVOUpdateInput categoryVOInput) {
		CategorySchema categoryToInsert = ModelMapperMaping.parseObject(categoryVOInput, CategorySchema.class);
		
		categoryRepository.save(categoryToInsert);
	}

	@Override
	public void deleteCategory(Long id) {
		categoryRepository.deleteById(id);
	}
}
