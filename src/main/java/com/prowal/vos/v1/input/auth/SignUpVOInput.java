package com.prowal.vos.v1.input.auth;

import java.io.Serializable;
import java.util.Objects;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class SignUpVOInput implements Serializable {

	private static final long serialVersionUID = 1L;

	@NotNull(message = "UserName should not be null")
	@NotBlank(message = "UserName should not be blank")
	private String userName;
	
	@NotNull(message = "FirstName should not be null")
	@NotBlank(message = "FirstName should not be blank")
	private String firstName;
	
	@NotNull(message = "LastName should not be null")
	@NotBlank(message = "LastName should not be blank")
	private String lastName;
	
	@NotNull(message = "Password should not be null")
	@NotBlank(message = "Password should not be blank")
	private String password;

	public SignUpVOInput() {
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public int hashCode() {
		return Objects.hash(firstName, lastName, password, userName);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SignUpVOInput other = (SignUpVOInput) obj;
		return Objects.equals(firstName, other.firstName) && Objects.equals(lastName, other.lastName)
				&& Objects.equals(password, other.password) && Objects.equals(userName, other.userName);
	}
}