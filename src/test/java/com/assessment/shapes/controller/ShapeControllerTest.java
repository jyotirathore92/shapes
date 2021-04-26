package com.assessment.shapes.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doNothing;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindingResult;

import com.assessment.shapes.config.ShapeTestConfig;
import com.assessment.shapes.dto.GeometryDescriptor;
import com.assessment.shapes.dto.ShapesRequestDTO;
import com.assessment.shapes.response.dto.ShapesResponseDTO;
import com.assessment.shapes.service.ShapeService;
import com.assessment.shapes.validator.ShapesValidator;

public class ShapeControllerTest extends ShapeTestConfig {

	@InjectMocks
	private ShapeController shapeController;

	@Mock
	private ShapesValidator shapeValidator;

	@Mock
	private ShapeService shapeService;

	@Test
	public void addShapeTest() {
		BindingResult bindingResult = new BeanPropertyBindingResult(new ShapesRequestDTO(), "request");
		ShapesRequestDTO request = ShapesRequestDTO.builder().name("Square").type("Square")
				.geometricDescriptor(GeometryDescriptor.builder().length(1).xcoordinate(0).ycoordinate(0).build())
				.build();
		doNothing().when(shapeValidator).validate(request, bindingResult);
		ShapesResponseDTO response = ShapesResponseDTO.builder().id(1).isShapeAlreadyPresent(false).length(1)
				.xcoordinate(0).ycoordinate(0).name("Square").type("Square").build();
		Mockito.when(shapeService.addShape(request)).thenReturn(response);
		String success = response.isShapeAlreadyPresent() ? "Shape is already present": "Shape successfully saved";
		shapeController.addShape(request, bindingResult);
		assertEquals(success, "Shape successfully saved");
	}
	
	@Test
	public void addAlreadyExistingShapeTest() {
		BindingResult bindingResult = new BeanPropertyBindingResult(new ShapesRequestDTO(), "request");
		ShapesRequestDTO request = ShapesRequestDTO.builder().name("Square").type("Square")
				.geometricDescriptor(GeometryDescriptor.builder().length(1).xcoordinate(0).ycoordinate(0).build())
				.build();
		doNothing().when(shapeValidator).validate(request, bindingResult);
		ShapesResponseDTO response = ShapesResponseDTO.builder().id(1).isShapeAlreadyPresent(true).length(1)
				.xcoordinate(0).ycoordinate(0).name("Square").type("Square").build();
		Mockito.when(shapeService.addShape(request)).thenReturn(response);
		String success = response.isShapeAlreadyPresent() ? "Shape is already present": "Shape successfully saved";
		shapeController.addShape(request, bindingResult);
		assertEquals(success, "Shape is already present");
	}
	
	@Test
	public void getShapesTest() {
		
		List<ShapesResponseDTO> shapesResponseDtoList = new ArrayList<>();
		shapesResponseDtoList.add(ShapesResponseDTO.builder().id(1).isShapeAlreadyPresent(false).length(1)
				.xcoordinate(0).ycoordinate(0).name("Square").type("Square").build());
		Mockito.when(shapeService.getShapes())
				.thenReturn(shapesResponseDtoList);
		
		
		ShapesResponseDTO response = ShapesResponseDTO.builder().id(1).isShapeAlreadyPresent(false).length(1)
		.xcoordinate(0).ycoordinate(0).name("Square").type("Square").build();
		shapeController.getShapes();
		assertEquals(response.getLength(), 1);
		assertEquals(response.getXcoordinate(), 0);
		assertEquals(response.getYcoordinate(), 0);
	}

}
