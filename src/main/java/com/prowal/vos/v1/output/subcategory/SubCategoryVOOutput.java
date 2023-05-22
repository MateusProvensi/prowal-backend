package com.prowal.vos.v1.output.subcategory;

import java.io.Serializable;
import java.time.Instant;

import org.springframework.hateoas.RepresentationModel;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.dozermapper.core.Mapping;
import com.prowal.vos.v1.output.category.CategoryVOOutput;

public class SubCategoryVOOutput extends RepresentationModel<SubCategoryVOOutput> implements Serializable {
	private static final long serialVersionUID = 1L;

	@Mapping(value = "id")
	@JsonProperty(value = "id")
	private Long key;

	private String description;

	private CategoryVOOutput category;

	private Instant createdAt;

	private Instant updatedAt;

	public Long getKey() {
		return key;
	}

	public void setKey(Long key) {
		this.key = key;
	}

	public Instant getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Instant createdAt) {
		this.createdAt = createdAt;
	}

	public Instant getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Instant updatedAt) {
		this.updatedAt = updatedAt;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public CategoryVOOutput getCategory() {
		return category;
	}

	public void setCategory(CategoryVOOutput category) {
		this.category = category;
	}
}
