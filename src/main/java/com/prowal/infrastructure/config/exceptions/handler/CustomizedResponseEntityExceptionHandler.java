package com.prowal.infrastructure.config.exceptions.handler;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.prowal.infrastructure.config.exceptions.EntityExistsException;
import com.prowal.infrastructure.config.exceptions.ExceptionResponse;
import com.prowal.infrastructure.config.exceptions.FieldValidationException;
import com.prowal.infrastructure.config.exceptions.InvalidJwtAuthenticationException;
import com.prowal.infrastructure.config.exceptions.RequiredObjectIsNullException;
import com.prowal.infrastructure.config.exceptions.ResourceNotFoundException;
import com.prowal.infrastructure.config.exceptions.UserDoesNotTheSameOfTheEntity;
import com.prowal.infrastructure.config.exceptions.utilExceptionClass.Field;

@ControllerAdvice
@RestController
public class CustomizedResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {
	@ExceptionHandler(UserDoesNotTheSameOfTheEntity.class)
	public final ResponseEntity<ExceptionResponse> handleUserDoesNotTheSameExceptions(Exception ex, WebRequest request) {
		ExceptionResponse exceptionResponse = new ExceptionResponse(
				new Date(),
				ex.getMessage(),
				request.getDescription(false));
		
		return new ResponseEntity<>(exceptionResponse, HttpStatus.CONFLICT);
	}

	@ExceptionHandler(ResourceNotFoundException.class)
	public final ResponseEntity<ExceptionResponse> handleNotFoundExceptions(Exception ex, WebRequest request) {
		ExceptionResponse exceptionResponse = new ExceptionResponse(
				new Date(),
				ex.getMessage(),
				request.getDescription(false));
		
		return new ResponseEntity<>(exceptionResponse, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(EntityExistsException.class)
	public final ResponseEntity<ExceptionResponse> handleEntityExistsExceptionExceptions(Exception ex, WebRequest request) {
		ExceptionResponse exceptionResponse = new ExceptionResponse(
				new Date(),
				ex.getMessage(),
				request.getDescription(false));
		
		return new ResponseEntity<>(exceptionResponse, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(RequiredObjectIsNullException.class)
	public final ResponseEntity<ExceptionResponse> handleBadRequestExceptions(Exception ex, WebRequest request) {
		ExceptionResponse exceptionResponse = new ExceptionResponse(
				new Date(),
				ex.getMessage(),
				request.getDescription(false));
		
		return new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(InvalidJwtAuthenticationException.class)
	@ResponseStatus(HttpStatus.FORBIDDEN)
	public final
	ResponseEntity<ExceptionResponse>
	handleInvalidJwtAuthenticationExceptions(Exception ex, WebRequest request) {
		
		ExceptionResponse exceptionResponse = new ExceptionResponse(
				new Date(),
				ex.getMessage(),
				request.getDescription(false));
		
		return new ResponseEntity<>(exceptionResponse, HttpStatus.FORBIDDEN);
	}
	
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(
			MethodArgumentNotValidException ex,
			HttpHeaders headers,
			HttpStatusCode status,
			WebRequest request) {
		List<Field> errors = ex
				.getBindingResult()
				.getFieldErrors()
				.stream()
				.map(error -> new Field(error.getField(), error.getDefaultMessage()))
				.collect(Collectors.toList());
		
		String defaultMessageGeneral = "Some fields need to be reconsidered";
		
		FieldValidationException fieldValidationException = new FieldValidationException(
				HttpStatus.BAD_REQUEST,
				defaultMessageGeneral,
				errors);
		
		return handleExceptionInternal(
				ex,
				fieldValidationException,
				headers,
				fieldValidationException.getStatus(),
				request);
	}

	@ExceptionHandler(Exception.class)
	public final ResponseEntity<ExceptionResponse> handleAllExceptions(Exception ex, WebRequest request) {
		ExceptionResponse exceptionResponse = new ExceptionResponse(
				new Date(),
				"Internal server error",
				request.getDescription(false));

		return new ResponseEntity<>(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
