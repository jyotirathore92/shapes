package com.assessment.shapes.response.dto;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ShapesResponseDTO implements Serializable{
	
	private static final long serialVersionUID = 1L;

	private long id;
    private String type;
	private String name;
	private int length;
	private int xcoordinate;
	private int ycoordinate;
	private boolean isShapeAlreadyPresent;
	
}
