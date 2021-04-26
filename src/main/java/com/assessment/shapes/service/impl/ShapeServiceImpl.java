package com.assessment.shapes.service.impl;

import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.assessment.shapes.dto.ShapesRequestDTO;
import com.assessment.shapes.mapper.ShapeMapper;
import com.assessment.shapes.model.ShapeDetails;
import com.assessment.shapes.repository.ShapesRepository;
import com.assessment.shapes.response.dto.ShapesResponseDTO;
import com.assessment.shapes.service.ShapeService;

import lombok.extern.slf4j.Slf4j;

/**
 * Service class containing the business logic to add and retrieve Shapes
 * (Squares)
 * 
 *
 * @author Jyoti
 */

@Service
@Slf4j
public class ShapeServiceImpl implements ShapeService {

	@Autowired
	ShapeMapper shapeMapper;

	@Autowired
	ShapesRepository shapesRepository;

	/**
	 * Method to add the Shape (Square) details that comes from the client into the
	 * database. It maintains the transaction and rollsback in case of any issue.
	 * Using Rectangle2D class to find intersection among the square shapes stored.
	 * Assuming square is a special rectangle.
	 *
	 * @param ShapesRequestDTO object which contains the request attributes for
	 *                         adding Square shapes in the application
	 * @return ShapesResponseDTO response after pushing the data into the database
	 */

	@Override
	@Transactional(rollbackFor = Exception.class)
	public ShapesResponseDTO addShape(ShapesRequestDTO shapeReqDto) {
		log.info("Add shape for : {}", shapeReqDto.getName());
		ShapesResponseDTO shapesResponse = new ShapesResponseDTO();
		Rectangle2D square = new Rectangle2D.Float(shapeReqDto.getGeometricDescriptor().getXcoordinate(),
				shapeReqDto.getGeometricDescriptor().getYcoordinate(), shapeReqDto.getGeometricDescriptor().getLength(),
				shapeReqDto.getGeometricDescriptor().getLength());

		List<ShapeDetails> shapeDetailsList = (List<ShapeDetails>) shapesRepository.findAll();
		List<Rectangle2D> squareList = new ArrayList<>();

		shapeDetailsList.stream().forEach(shape -> {
			Rectangle2D squareInstance = new Rectangle2D.Float(shape.getXcoordinate(), shape.getYcoordinate(),
					shape.getLength(), shape.getLength());
			squareList.add(squareInstance);
		});

		for (Rectangle2D squareElement : squareList) {
			boolean ifIntersects = square.intersects(squareElement);
			if (ifIntersects) {
				shapesResponse.setShapeAlreadyPresent(true);
				return shapesResponse;
			}
		}

		ShapeDetails shapeDetails = shapeMapper.mapToShapeDetails(shapeReqDto);
		shapeDetails.setCreationDate(new Date());
		ShapeDetails shapeDetailsAfterSaving = shapesRepository.save(shapeDetails);
		shapesResponse.setId(shapeDetailsAfterSaving.getId());
		return shapesResponse;

	}
	
	/**
	 * Method to get all the Shapes
	 *                         
	 * @return List<ShapeDetails> contains all the shapes
	 *         
	 */

	@Override
	public List<ShapesResponseDTO> getShapes() {

		List<ShapeDetails> shapeDetailsList = (List<ShapeDetails>) shapesRepository.findAll();
		List<ShapesResponseDTO> shapesList = shapeMapper.mapToShapeResponse(shapeDetailsList);
		return shapesList;
	}

}
