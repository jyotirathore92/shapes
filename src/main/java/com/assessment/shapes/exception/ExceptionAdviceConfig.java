package com.assessment.shapes.exception;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.assessment.shapes.response.dto.ExceptionResponseDTO;

import lombok.extern.slf4j.Slf4j;

/**
 * Exception config class which has entries for all the exceptions be it user
 * defined or not for exception handling purpose. It is annotated with
 * RestControllerAdvice. Each method returns a response entity of
 * ExceptionResponseDTO with httpStatus and error message.
 * 
 *
 * @author Jyoti
 */

@RestControllerAdvice
@Slf4j
public class ExceptionAdviceConfig {

	@ExceptionHandler(Exception.class)
	public ResponseEntity<ExceptionResponseDTO> exception(Exception ex) {
		log.error("exception : ", ex);
		HttpStatus httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
		ExceptionResponseDTO error = new ExceptionResponseDTO(httpStatus.value(), httpStatus.getReasonPhrase(), null);
		return new ResponseEntity<ExceptionResponseDTO>(error, httpStatus);
	}

	@ExceptionHandler(BadRequestException.class)
	public ResponseEntity<ExceptionResponseDTO> badRequestException(BadRequestException ex) {
		log.error("exception :{} ", ex.getErrors());
		HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
		ExceptionResponseDTO error = new ExceptionResponseDTO(httpStatus.value(), ex.getMessage(), ex.getErrors());
		return new ResponseEntity<ExceptionResponseDTO>(error, httpStatus);
	}

	@ExceptionHandler(HttpMessageNotReadableException.class)
	public ResponseEntity<ExceptionResponseDTO> httpMessageNotReadableException(Exception ex) {
		log.error("exception : ", ex);
		HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
		ExceptionResponseDTO error = new ExceptionResponseDTO(httpStatus.value(), httpStatus.getReasonPhrase(), null);
		return new ResponseEntity<ExceptionResponseDTO>(error, httpStatus);
	}
	
	@ExceptionHandler(DataIntegrityViolationException.class)
	public ResponseEntity<ExceptionResponseDTO> DataIntegrityViolationException(Exception ex) {
		log.error("exception : ", ex);
		HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
		ExceptionResponseDTO error = new ExceptionResponseDTO(httpStatus.value(), "Shape name already present", null);
		return new ResponseEntity<ExceptionResponseDTO>(error, httpStatus);
	}
}
