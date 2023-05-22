package com.prowal.infrastructure.config.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class WrongTypeEnumException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public WrongTypeEnumException(String ex) {
		super(ex);
	}
}
