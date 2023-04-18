package com.prowal.infrastructure.config.exceptions.utilExceptionClass;

public class Field {
	private String name;
	private String errorMessage;

	public Field(String name, String errorMessage) {
		this.name = name;
		this.errorMessage = errorMessage;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

}
