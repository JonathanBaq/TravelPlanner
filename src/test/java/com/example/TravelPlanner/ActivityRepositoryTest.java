package com.example.TravelPlanner;

import static org.assertj.core.api.Assertions.assertThat;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.TravelPlanner.domain.Activity;
import com.example.TravelPlanner.domain.ActivityRepository;
import com.example.TravelPlanner.domain.City;
import com.example.TravelPlanner.domain.CityRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ActivityRepositoryTest {
	@Autowired
	private ActivityRepository arepository;

	@Autowired
	private CityRepository crepository;

	@Test
	public void findByNameShouldReturnCity() {
		List<Activity> activities = arepository.findByName("Stay at Hampton by Hilton Berlin West");

		assertThat(activities).hasSize(1);
		assertThat(activities.get(0).getCity().getName()).isEqualTo("Berlin");
	}

	@Test
	public void createNewActivity() {
		City newCity = new City("Garmisch-Partenkirchen");
		crepository.save(newCity);
		Activity activity = new Activity("Train and cable car ride to Zugspitze", "2.1.2022 11:00",
				crepository.findByName("Garmisch-Partenkirchen").get(0),
				"Check email and show confirmation at location");
		arepository.save(activity);
		assertThat(activity.getId()).isNotNull();
	}

	@Test
	public void deleteActivity() {
		List<Activity> activity = arepository.findByName("Stay at Hampton by Hilton Berlin West");

		arepository.deleteById(activity.get(0).getId());
		assertThat(arepository.count()).isEqualTo(4);
	}
}
