package com.assessment.shapes.validator;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;

import com.assessment.shapes.config.ShapeTestConfig;
import com.assessment.shapes.dto.GeometryDescriptor;
import com.assessment.shapes.dto.ShapesRequestDTO;
import com.assessment.shapes.exception.BadRequestException;

public class ShapesValidatorTest extends ShapeTestConfig {

	@InjectMocks
	private ShapesValidator shapeValidator;

	@Test
	public void supportsTest() {
		assertTrue(shapeValidator.supports(ShapesRequestDTO.class));
	}

	@Test
	public void nameValidityTest() {
		ShapesRequestDTO request = ShapesRequestDTO.builder().name("").type("Square")
				.geometricDescriptor(GeometryDescriptor.builder().length(1).xcoordinate(0).ycoordinate(0).build())
				.build();
		Errors errors = new BeanPropertyBindingResult(request, "ShapesRequestDTO");
		try {
			shapeValidator.validate(request, errors);
		} catch (BadRequestException ex) {
			assertTrue(errors.hasErrors());
			assertNotNull(errors.getFieldError(ShapesValidator.NAME));
		}
	}
	
	@Test
	public void nameNullValidityTest() {
		ShapesRequestDTO request = ShapesRequestDTO.builder().name(null).type("Square")
				.geometricDescriptor(GeometryDescriptor.builder().length(1).xcoordinate(0).ycoordinate(0).build())
				.build();
		Errors errors = new BeanPropertyBindingResult(request, "ShapesRequestDTO");
		try {
			shapeValidator.validate(request, errors);
		} catch (BadRequestException ex) {
			assertTrue(errors.hasErrors());
			assertNotNull(errors.getFieldError(ShapesValidator.NAME));
		}
	}
	
	@Test
	public void typeValidityTest() {
		ShapesRequestDTO request = ShapesRequestDTO.builder().name("Square").type("")
				.geometricDescriptor(GeometryDescriptor.builder().length(1).xcoordinate(0).ycoordinate(0).build())
				.build();
		Errors errors = new BeanPropertyBindingResult(request, "ShapesRequestDTO");
		try {
			shapeValidator.validate(request, errors);
		} catch (BadRequestException ex) {
			assertTrue(errors.hasErrors());
			assertNotNull(errors.getFieldError(ShapesValidator.TYPE));
		}
	}
	
	@Test
	public void typeNullValidityTest() {
		ShapesRequestDTO request = ShapesRequestDTO.builder().name("Square").type(null)
				.geometricDescriptor(GeometryDescriptor.builder().length(1).xcoordinate(0).ycoordinate(0).build())
				.build();
		Errors errors = new BeanPropertyBindingResult(request, "ShapesRequestDTO");
		try {
			shapeValidator.validate(request, errors);
		} catch (BadRequestException ex) {
			assertTrue(errors.hasErrors());
			assertNotNull(errors.getFieldError(ShapesValidator.TYPE));
		}
	}
	
	@Test
	public void lengthValidityTest() {
		ShapesRequestDTO request = ShapesRequestDTO.builder().name("Square").type("Square")
				.geometricDescriptor(GeometryDescriptor.builder().length(0).xcoordinate(0).ycoordinate(0).build())
				.build();
		Errors errors = new BeanPropertyBindingResult(request, "ShapesRequestDTO");
		try {
			shapeValidator.validate(request, errors);
		} catch (BadRequestException ex) {
			assertTrue(errors.hasErrors());
		}
	}

}
