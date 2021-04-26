package com.assessment.shapes.model;

import static javax.persistence.GenerationType.SEQUENCE;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class ShapeDetails implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "ID", unique = true, nullable = false)
	@GeneratedValue(strategy=SEQUENCE, generator = "SEQ")
	@SequenceGenerator(name = "SEQ", sequenceName = "SHAPE_SEQ", allocationSize = 1)
	private long id;
	
	@Column(name = "TYPE", nullable = false)
	private String type;
	
	@Column(name = "NAME", nullable = false, unique = true)
	private String name;

	@Column(name = "LENGTH", nullable = false)
	private int length;
	
	@Column(name = "X_COORDINATE", nullable = false)
	private int xcoordinate;
	
	@Column(name = "Y_COORDINATE", nullable = false)
	private int ycoordinate;
	
	@Column(name = "CREATION_DATE")
	private Date creationDate;
}
