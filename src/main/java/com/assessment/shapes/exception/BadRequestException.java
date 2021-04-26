package com.assessment.shapes.exception;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Custom defined Exception class with parameterized constructors for different parameter types
 *
 * @author Jyoti
 */

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class BadRequestException extends RuntimeException{

	private static final long serialVersionUID = 1L;
	private final List<ObjectError> errors = new ArrayList<>();

	public BadRequestException(String exception) {
        super(exception);
    }
	
	public BadRequestException(List<ObjectError> errors) {
		this(HttpStatus.BAD_REQUEST.getReasonPhrase());
		if(errors != null) {
			this.errors.addAll(errors);
		}
    }
	
	public List<ObjectError> getErrors() {
		return errors;
	}
}
