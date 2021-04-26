package com.assessment.shapes;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * This class is the triggering point of the Shape Application Micro-service
 * which provides API's for adding shapes and viewing all the shapes 
 * present in the application.
 *
 * @author Jyoti
 */

@SpringBootApplication
public class ShapeApplication {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SpringApplication.run(ShapeApplication.class, args);
	}

}
