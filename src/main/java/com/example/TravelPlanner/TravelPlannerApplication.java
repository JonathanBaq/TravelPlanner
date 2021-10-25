package com.example.TravelPlanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.example.TravelPlanner.domain.Activity;
import com.example.TravelPlanner.domain.ActivityRepository;
import com.example.TravelPlanner.domain.City;
import com.example.TravelPlanner.domain.CityRepository;
import com.example.TravelPlanner.domain.User;
import com.example.TravelPlanner.domain.UserRepository;

@SpringBootApplication
public class TravelPlannerApplication {
	private static final Logger log = LoggerFactory.getLogger(TravelPlannerApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(TravelPlannerApplication.class, args);
	}

	@Autowired
	private ActivityRepository arepository;

	@Autowired
	private CityRepository crepository;

	@Autowired
	private UserRepository urepository;

	@Bean
	CommandLineRunner runner() {
		return args -> {
			// Add City objects and save to db
			City city1 = new City("Berlin");
			City city2 = new City("Munich");
			crepository.save(city1);
			crepository.save(city2);

			// Test Data
			Activity activity = new Activity("Stay at Hampton by Hilton Berlin West", "1.1.2022 - 2.1.2022", city1,
					"Link to Booking.com reservation");
			arepository.save(activity);
			activity = new Activity("Hop-on-hop-off City Tour", "1.1.2022", city1, "Link to BigBus tours e-ticket");
			arepository.save(activity);
			activity = new Activity("Train to Munich", "1.2.2022 19:00", city1, "Link to train e-ticket");
			arepository.save(activity);
			activity = new Activity("Car Rental at Europcar", "1.2.2022 19:00", city2,
					"Central Train Station, show driver's license");
			arepository.save(activity);

			// Create users: admin/admin user/user
			User user1 = new User("user", "USER", "$2a$10$Ho3mcGwGAiE/y896/SeEvus89L7SNS8h8EHqvO2UGjqYc2JARr..K");
			User user2 = new User("admin", "ADMIN", "$2a$10$lrthSAfYPE5eFJTFNJeWOej.wUST/FrUUKfq71Bxz4xxcmNLy4cvW");
			urepository.save(user1);
			urepository.save(user2);

			log.info("fetch all activities");
			for (Activity factivity : arepository.findAll()) {
				log.info(factivity.toString());
			}
		};
	}
}
