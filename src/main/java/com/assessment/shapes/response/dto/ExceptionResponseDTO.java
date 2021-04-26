package com.assessment.shapes.response.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.validation.ObjectError;

@Data
@AllArgsConstructor
public class ExceptionResponseDTO {

	private int status;
	private String message;
	private List<ObjectError> errors;

}
