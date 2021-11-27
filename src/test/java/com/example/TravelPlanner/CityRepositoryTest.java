package com.example.TravelPlanner;

import static org.assertj.core.api.Assertions.assertThat;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.TravelPlanner.domain.City;
import com.example.TravelPlanner.domain.CityRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CityRepositoryTest {
	@Autowired
	private CityRepository crepository;

	@Test
	public void findCategoryByName() {
		List<City> cities = crepository.findByName("Munich");
		assertThat(cities.get(0).getName()).isEqualTo("Munich");
	}

	@Test
	public void createNewCity() {
		City city = new City("Garmisch-Partenkirchen");
		crepository.save(city);
		assertThat(city.getCityId()).isNotNull();
	}

	@Test
	public void deleteCity() {
		List<City> cities = crepository.findByName("Berlin");

		crepository.deleteById(cities.get(0).getCityId());
		assertThat(crepository.count()).isEqualTo(3);
	}
}
