package com.assessment.shapes.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import com.assessment.shapes.dto.ShapesRequestDTO;
import com.assessment.shapes.model.ShapeDetails;
import com.assessment.shapes.response.dto.ShapesResponseDTO;

/**
 * Mapper interface to map the ShapesRequestDTO to ShapeDetails
 * model and to map List<ShapeDetails> to List<ShapesResponseDTO>.
 * 
 *
 * @author Jyoti
 */

@Mapper(componentModel = "spring")
public interface ShapeMapper {

	ShapeMapper INSTANCE = Mappers.getMapper(ShapeMapper.class);

	@Mappings({ @Mapping(source = "type", target = "type"), @Mapping(source = "name", target = "name"),
			@Mapping(source = "shapesReqDto.geometricDescriptor.length", target = "length"),
			@Mapping(source = "shapesReqDto.geometricDescriptor.xcoordinate", target = "xcoordinate"),
			@Mapping(source = "shapesReqDto.geometricDescriptor.ycoordinate", target = "ycoordinate") })
	ShapeDetails mapToShapeDetails(ShapesRequestDTO shapesReqDto);

	@Mappings({ @Mapping(source = "type", target = "type"), @Mapping(source = "name", target = "name"),
			@Mapping(source = "length", target = "length"),@Mapping(source = "id", target = "id"),
			@Mapping(source = "xcoordinate", target = "xcoordinate"),
			@Mapping(source = "ycoordinate", target = "ycoordinate") })
	List<ShapesResponseDTO> mapToShapeResponse(List<ShapeDetails> shapesDetails);

}
