package com.assessment.shapes.config;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import com.assessment.shapes.ShapeApplication;

@ActiveProfiles("test")
@ExtendWith(MockitoExtension.class)
@SpringBootTest(classes = ShapeApplication.class)
public class ShapeTestConfig {

}
