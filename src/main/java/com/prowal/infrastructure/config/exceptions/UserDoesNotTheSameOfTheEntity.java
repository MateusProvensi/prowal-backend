package com.prowal.infrastructure.config.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class UserDoesNotTheSameOfTheEntity extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public UserDoesNotTheSameOfTheEntity(String ex) {
		super(ex);
	}
}
