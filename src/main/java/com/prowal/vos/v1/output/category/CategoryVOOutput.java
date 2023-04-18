package com.prowal.vos.v1.output.category;

import java.io.Serializable;

import org.springframework.hateoas.RepresentationModel;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.dozermapper.core.Mapping;
import com.prowal.infrastructure.config.db.schema.category.TypeCategory;
import com.prowal.vos.v1.output.user.UserVOOutput;

public class CategoryVOOutput extends RepresentationModel<CategoryVOOutput> implements Serializable {

	private static final long serialVersionUID = 1L;

	@Mapping(value = "id")
	@JsonProperty(value = "id")
	private Long key;

	private String description;

	private TypeCategory type;

	private UserVOOutput user;

	public Long getKey() {
		return key;
	}

	public void setKey(Long key) {
		this.key = key;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public TypeCategory getType() {
		return type;
	}

	public void setType(TypeCategory type) {
		this.type = type;
	}

	public UserVOOutput getUser() {
		return user;
	}

	public void setUser(UserVOOutput user) {
		this.user = user;
	}
}
