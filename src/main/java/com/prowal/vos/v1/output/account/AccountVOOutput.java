package com.prowal.vos.v1.output.account;

import java.io.Serializable;
import java.time.Instant;

import org.springframework.hateoas.RepresentationModel;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.prowal.vos.v1.output.user.UserVOOutput;

public class AccountVOOutput extends RepresentationModel<AccountVOOutput> implements Serializable {

	private static final long serialVersionUID = 1L;

	@JsonProperty(value = "id")
	private Long key;

	private String description;

	private Boolean enabled;

	private Instant createdAt;

	private Instant updatedAt;

	private UserVOOutput user;

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

	public String getDescription() {
		return description;
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

	public void setDescription(String description) {
		this.description = description;
	}

	public UserVOOutput getUser() {
		return user;
	}

	public void setUser(UserVOOutput user) {
		this.user = user;
	}
}
