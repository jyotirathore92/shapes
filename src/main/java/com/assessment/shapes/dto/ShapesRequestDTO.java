package com.assessment.shapes.dto;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ShapesRequestDTO implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@NotNull
	private String type;
	
	@NotNull
	private String name;
	
	private GeometryDescriptor geometricDescriptor;
}
