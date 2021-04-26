package com.assessment.shapes.service;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import com.assessment.shapes.config.ShapeTestConfig;
import com.assessment.shapes.dto.GeometryDescriptor;
import com.assessment.shapes.dto.ShapesRequestDTO;
import com.assessment.shapes.mapper.ShapeMapper;
import com.assessment.shapes.model.ShapeDetails;
import com.assessment.shapes.repository.ShapesRepository;
import com.assessment.shapes.response.dto.ShapesResponseDTO;
import com.assessment.shapes.service.impl.ShapeServiceImpl;

public class ShapeServiceTest extends ShapeTestConfig {

	@InjectMocks
	private ShapeServiceImpl shapeServiceImpl;

	@Mock
	ShapeMapper shapeMapper;

	@Mock
	ShapesRepository shapeRepository;

	@Test
	public void addShapeTest() {

		ShapesRequestDTO request = ShapesRequestDTO.builder().name("Square").type("Square")
				.geometricDescriptor(GeometryDescriptor.builder().length(1).xcoordinate(0).ycoordinate(0).build())
				.build();

		ShapeDetails shapeDetails = ShapeDetails.builder().id(1).length(1).name("Square1").type("Square").xcoordinate(1)
				.ycoordinate(1).build();

		ShapeDetails shapeDetails1 = ShapeDetails.builder().id(1).length(1).name("Square2").type("Square")
				.xcoordinate(2).ycoordinate(2).build();

		List<ShapeDetails> shapeDetailsList = new ArrayList<>();
		shapeDetailsList.add(shapeDetails);
		shapeDetailsList.add(shapeDetails1);

		ShapeDetails shapeDetailsAfterSaving = ShapeDetails.builder().id(1).length(1).name("Square1").type("Square")
				.xcoordinate(0).ycoordinate(0).creationDate(new Date()).build();

		when(shapeRepository.findAll()).thenReturn(shapeDetailsList);
		when(shapeMapper.mapToShapeDetails(request)).thenReturn(shapeDetails);
		when(shapeRepository.save(shapeDetails)).thenReturn(shapeDetailsAfterSaving);

		shapeServiceImpl.addShape(request);

		verify(shapeRepository, times(1)).save(shapeDetails);
	}
	
	@Test
	public void addShapeWithIntersectionTest() {

		ShapesRequestDTO request = ShapesRequestDTO.builder().name("Square").type("Square")
				.geometricDescriptor(GeometryDescriptor.builder().length(1).xcoordinate(0).ycoordinate(0).build())
				.build();

		ShapeDetails shapeDetails = ShapeDetails.builder().id(1).length(1).name("Square1").type("Square").xcoordinate(0)
				.ycoordinate(0).build();

		ShapeDetails shapeDetails1 = ShapeDetails.builder().id(1).length(1).name("Square2").type("Square")
				.xcoordinate(2).ycoordinate(2).build();

		List<ShapeDetails> shapeDetailsList = new ArrayList<>();
		shapeDetailsList.add(shapeDetails);
		shapeDetailsList.add(shapeDetails1);

		when(shapeRepository.findAll()).thenReturn(shapeDetailsList);

		shapeServiceImpl.addShape(request);

		verify(shapeRepository, times(1)).findAll();
	}
	
	@Test
	public void getShapesTest() {

		ShapeDetails shapeDetails = ShapeDetails.builder().id(1).length(1).name("Square1").type("Square").xcoordinate(0)
				.ycoordinate(0).build();

		ShapeDetails shapeDetails1 = ShapeDetails.builder().id(1).length(1).name("Square2").type("Square")
				.xcoordinate(2).ycoordinate(2).build();

		List<ShapeDetails> shapeDetailsList = new ArrayList<>();
		shapeDetailsList.add(shapeDetails);
		shapeDetailsList.add(shapeDetails1);
		
		List<ShapesResponseDTO> shapesResponseDtoList = new ArrayList<>();
		shapesResponseDtoList.add(ShapesResponseDTO.builder().id(1).isShapeAlreadyPresent(false).length(1)
				.xcoordinate(0).ycoordinate(0).name("Square1").type("Square").build());
		shapesResponseDtoList.add(ShapesResponseDTO.builder().id(1).isShapeAlreadyPresent(false).length(1)
				.xcoordinate(2).ycoordinate(2).name("Square2").type("Square").build());

		when(shapeRepository.findAll()).thenReturn(shapeDetailsList);
		when(shapeMapper.mapToShapeResponse(shapeDetailsList)).thenReturn(shapesResponseDtoList);
		shapeServiceImpl.getShapes();

		verify(shapeRepository, times(1)).findAll();
	}
}
