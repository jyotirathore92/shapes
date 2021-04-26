package com.assessment.shapes.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.assessment.shapes.dto.ShapesRequestDTO;
import com.assessment.shapes.exception.BadRequestException;

/**
 * Class to validate the input parameters which are present in the request
 * object
 *
 * @author Jyoti
 */

@Component
public class ShapesValidator implements Validator {

	static final String NAME = "name";
	static final String TYPE = "type";
	
	@Override
	public boolean supports(Class<?> clazz) {
		return ShapesRequestDTO.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		
		ShapesRequestDTO shapesRequestDto = (ShapesRequestDTO) target;
		
		if (null == shapesRequestDto.getName() || shapesRequestDto.getName().isBlank()) {
			errors.rejectValue(NAME, "Please enter name");
		}
		if (null == shapesRequestDto.getType() || shapesRequestDto.getType().isBlank()) {
			errors.rejectValue(TYPE, "Please enter type");
		}
		if (shapesRequestDto.getGeometricDescriptor().getLength() == 0) {
			errors.rejectValue(null, "Please enter length");
		}
		
		if (errors.hasErrors()) {
			throw new BadRequestException(errors.getAllErrors());
		}
	}

}
