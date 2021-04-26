package com.assessment.shapes.dto;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class GeometryDescriptor implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@NotNull
	private int xcoordinate;
	
	@NotNull
	private int ycoordinate;
	
	@NotNull
	private int length;
}
