package com.prowal.infrastructure.config.db.schema.subcategory;

import java.io.Serializable;

import com.prowal.infrastructure.config.db.schema.category.CategorySchema;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Entity
@Table(name = "sub_categories")
public class SubCategorySchema implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sub_categories_sequence")
	@SequenceGenerator(name = "sub_categories_sequence", sequenceName = "sub_categories_sequence", allocationSize = 1)
	private Long id;

	@Column(name = "description")
	private String description;

	@ManyToOne
	@JoinColumn(name = "category_id")
	private CategorySchema category;

	public SubCategorySchema() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public CategorySchema getCategory() {
		return category;
	}

	public void setCategory(CategorySchema category) {
		this.category = category;
	}
}
