package com.prowal.vos.v1.input.category;

import java.io.Serializable;
import java.time.Instant;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.prowal.infrastructure.config.db.schema.category.TypeCategory;
import com.prowal.vos.v1.input.ids.UserIdInput;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class CategoryVOUpdateInput implements Serializable {

	private static final long serialVersionUID = 1L;

	@JsonIgnore
	private Long id;

	@NotNull(message = "Description should not be null")
	@NotBlank(message = "Description should not be blank")
	private String description;

	@JsonIgnore
	private UserIdInput user;

	@NotNull(message = "Type should not be null")
	private TypeCategory type;

	@JsonIgnore
	private Instant createdAt;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public UserIdInput getUser() {
		return user;
	}

	public void setUser(UserIdInput user) {
		this.user = user;
	}

	public Instant getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Instant createdAt) {
		this.createdAt = createdAt;
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
}
