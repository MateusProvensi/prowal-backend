package com.prowal.vos.v1.output.category;

import java.io.Serializable;
import java.time.Instant;

import org.springframework.hateoas.RepresentationModel;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.prowal.infrastructure.config.db.schema.category.TypeCategory;
import com.prowal.vos.v1.output.user.UserVOOutput;

public class CategoryVOOutput extends RepresentationModel<CategoryVOOutput> implements Serializable {

	private static final long serialVersionUID = 1L;

	@JsonProperty(value = "id")
	private Long key;

	private String description;

	private TypeCategory type;

	@JsonIgnore
	private UserVOOutput user;

	private Boolean enabled;

	private Instant createdAt;

	private Instant updatedAt;

	public Long getKey() {
		return key;
	}

	public void setKey(Long key) {
		this.key = key;
	}

	public Boolean getEnabled() {
		return enabled;
	}

	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
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
