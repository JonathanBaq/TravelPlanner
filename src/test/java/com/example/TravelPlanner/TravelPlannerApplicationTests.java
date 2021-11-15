package com.example.TravelPlanner;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.TravelPlanner.web.ActivityController;

@RunWith(SpringRunner.class)
@SpringBootTest
class TravelPlannerApplicationTests {
	// Smoke test: Testing that context creates controller
	@Autowired
	private ActivityController acontroller;

	@Test
	public void contextLoads() throws Exception {
		assertThat(acontroller).isNotNull();
	}
}
