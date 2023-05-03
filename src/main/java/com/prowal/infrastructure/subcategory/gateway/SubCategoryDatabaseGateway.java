package com.prowal.infrastructure.subcategory.gateway;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Component;

import com.prowal.entities.subcategory.gateway.SubCategoryGateway;
import com.prowal.infrastructure.config.db.repositories.SubCategoryRepository;
import com.prowal.infrastructure.config.db.schema.subcategory.SubCategorySchema;
import com.prowal.infrastructure.config.exceptions.EntityExistsException;
import com.prowal.infrastructure.config.mapper.ModelMapperMaping;
import com.prowal.vos.v1.input.subcategory.SubCategoryVOCreateInput;
import com.prowal.vos.v1.input.subcategory.SubCategoryVOUpdateInput;
import com.prowal.vos.v1.output.subcategory.SubCategoryVOOutput;

@Component
public class SubCategoryDatabaseGateway implements SubCategoryGateway {

	private final SubCategoryRepository subCategoryRepository;

	public SubCategoryDatabaseGateway(SubCategoryRepository subCategoryRepository) {
		this.subCategoryRepository = subCategoryRepository;
	}

	@Override
	public SubCategoryVOOutput findById(Long id) {
		Optional<SubCategorySchema> subCategory = subCategoryRepository.findById(id);

		SubCategoryVOOutput subCategoryVOOutput = subCategory
				.map(currentCategory -> ModelMapperMaping.parseObject(currentCategory, SubCategoryVOOutput.class))
				.orElseThrow(() -> new EntityExistsException("The Subcategory with ID = " + id + " does not exists."));

		return subCategoryVOOutput;
	}

	@Override
	public List<SubCategoryVOOutput> findByCategoryId(Long categoryId) {
		List<SubCategorySchema> categories = subCategoryRepository.findByCategoryId(categoryId);

		return ModelMapperMaping.parseListObjects(categories, SubCategoryVOOutput.class);
	}

	@Override
	public void createSubCategory(SubCategoryVOCreateInput subCategoryVOCreateInput) {
		SubCategorySchema subCategoryToInsert = ModelMapperMaping
				.parseObject(subCategoryVOCreateInput, SubCategorySchema.class);

		subCategoryRepository.save(subCategoryToInsert);
	}

	@Override
	public void updateSubCategory(SubCategoryVOUpdateInput subCategoryVOUpdateInput) {
		SubCategorySchema subCategoryToInsert = ModelMapperMaping
				.parseObject(subCategoryVOUpdateInput, SubCategorySchema.class);

		subCategoryRepository.save(subCategoryToInsert);
	}

	@Override
	public void deleteSubCategory(Long id) {
		subCategoryRepository.deleteById(id);
	}

}
