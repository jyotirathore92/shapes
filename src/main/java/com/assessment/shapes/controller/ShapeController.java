package com.assessment.shapes.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.assessment.shapes.dto.ShapesRequestDTO;
import com.assessment.shapes.response.dto.ShapesResponseDTO;
import com.assessment.shapes.service.ShapeService;
import com.assessment.shapes.validator.ShapesValidator;

import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

/**
 * Class is marked with the RestController annotation.It contains all the Shape
 * service applications apis with different request URIs
 *
 * @author Jyoti
 */

@RestController
@Slf4j
public class ShapeController {
	
	@Autowired
	private ShapesValidator shapesValidator;
	
	@Autowired
	private ShapeService shapeService;
	
	/**
	 * Method to add Shapes(Squares) that comes from the client into the
	 * database
	 *
	 * @param shapeReqDto 	   object which contains the request attributes for
	 *                         adding Shape(Square) details in the application
	 * @param result           instance of BindingRsult. Used for validation
	 *                         purpose.
	 * @return String as a response entity
	 */
	
	@PostMapping(value = "/addShape")
	@ApiOperation(value = "Add shape details", produces = "application/json")
	public ResponseEntity<String> addShape(@Valid @RequestBody ShapesRequestDTO shapeReqDto,
			BindingResult result) {
		log.info("Adding shape");
		shapesValidator.validate(shapeReqDto, result);
		ShapesResponseDTO shapesResponse = shapeService.addShape(shapeReqDto);
		if(shapesResponse.isShapeAlreadyPresent()) {
			return new ResponseEntity<String>("Shape is already present", HttpStatus.OK);
		}
		return new ResponseEntity<String>("Shape successfully saved", HttpStatus.OK);
	}
	
	/**
	 * Method to get the all shapes present in the application from the
	 * database
	 *                         
	 * @return List<ShapesResponseDTO> as a response entity
	 */
	
	@GetMapping(value = "/getShapes")
	@ApiOperation(value = "Add shape details", produces = "application/json")
	public ResponseEntity<List<ShapesResponseDTO>> getShapes() {
		log.info("Retrieving shapes");
		List<ShapesResponseDTO> shapesResponse = shapeService.getShapes();
		return new ResponseEntity<List<ShapesResponseDTO>>(shapesResponse, HttpStatus.OK);
	}

}
