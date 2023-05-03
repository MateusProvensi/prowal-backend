package com.prowal.vos.v1.output.account;

import java.io.Serializable;

import org.springframework.hateoas.RepresentationModel;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.prowal.vos.v1.output.user.UserVOOutput;

public class AccountVOOutput extends RepresentationModel<AccountVOOutput> implements Serializable {

	private static final long serialVersionUID = 1L;

	@JsonProperty(value = "id")
	private Long key;

	private String description;

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

	public UserVOOutput getUser() {
		return user;
	}

	public void setUser(UserVOOutput user) {
		this.user = user;
	}
}
