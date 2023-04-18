package com.prowal.infrastructure.config.exceptions;

import java.io.Serializable;
import java.util.List;

import org.springframework.http.HttpStatus;

import com.prowal.infrastructure.config.exceptions.utilExceptionClass.Field;

public class FieldValidationException implements Serializable {
	private static final long serialVersionUID = 1L;

	private HttpStatus status;
	private String message;
	private List<Field> errors;

	public FieldValidationException(HttpStatus status, String message, List<Field> errors) {
		this.status = status;
		this.message = message;
		this.errors = errors;
	}

	public HttpStatus getStatus() {
		return status;
	}

	public void setStatus(HttpStatus status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public List<Field> getErrors() {
		return errors;
	}

	public void setErrors(List<Field> errors) {
		this.errors = errors;
	}

}
