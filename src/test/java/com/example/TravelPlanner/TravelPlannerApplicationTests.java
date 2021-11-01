package com.example.TravelPlanner;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.example.TravelPlanner.web.ActivityController;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class TravelPlannerApplicationTests {
	// Smoke test: Testing that context creates controller
	@Autowired
	private ActivityController controller;

	@Test
	void contextLoads() throws Exception {
		assertThat(controller).isNotNull();
	}

}
