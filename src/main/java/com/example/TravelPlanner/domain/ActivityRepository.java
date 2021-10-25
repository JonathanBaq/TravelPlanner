package com.example.TravelPlanner.domain;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface ActivityRepository extends CrudRepository<Activity, Long> {

	List<Activity> findByName(String name);
}
