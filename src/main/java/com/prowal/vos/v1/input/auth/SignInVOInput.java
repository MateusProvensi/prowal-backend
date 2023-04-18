package com.prowal.vos.v1.input.auth;

import java.io.Serializable;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class SignInVOInput implements Serializable {

	private static final long serialVersionUID = 1L;

	@NotNull(message = "UserName should not be null")
	@NotBlank(message = "UserName should not be blank")
	private String userName;

	@NotNull(message = "Password should not be null")
	@NotBlank(message = "Password should not be blank")
	private String password;

	public SignInVOInput() {
	}
	
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
