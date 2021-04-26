package com.assessment.shapes.service;

import java.util.List;

import com.assessment.shapes.dto.ShapesRequestDTO;
import com.assessment.shapes.response.dto.ShapesResponseDTO;

public interface ShapeService {
	
	ShapesResponseDTO addShape(ShapesRequestDTO shapeReqDto);
	List<ShapesResponseDTO> getShapes();

}
