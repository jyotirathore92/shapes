package com.assessment.shapes.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.assessment.shapes.model.ShapeDetails;

@Repository
public interface ShapesRepository extends CrudRepository<ShapeDetails, Long>{

}
