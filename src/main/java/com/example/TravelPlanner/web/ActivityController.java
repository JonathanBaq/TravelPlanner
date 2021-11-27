package com.example.TravelPlanner.web;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.TravelPlanner.domain.Activity;
import com.example.TravelPlanner.domain.ActivityRepository;
import com.example.TravelPlanner.domain.City;
import com.example.TravelPlanner.domain.CityRepository;

@Controller
public class ActivityController {
	@Autowired
	private ActivityRepository arepository;

	@Autowired
	CityRepository crepository;

	// Get list of books from repository to activityList.html
	@GetMapping("/activitylist")
	public String getActivityList(Model model) {
		model.addAttribute("activityList", arepository.findAll());
		model.addAttribute("city", new City());
		model.addAttribute("cities", crepository.findAll());
		System.out.println("PROGRAM READS: " + arepository.findAll());
		return "activityList";
	}

	// Mapping for city filter
	@PostMapping(value = "/activitylist")
	public String cityFilter(City city, Model model) {
		List<Activity> activityList = arepository.findByCity(crepository.findById(city.getCityId()).get());

		model.addAttribute("activityList", activityList);
		model.addAttribute("city", new City());
		model.addAttribute("cities", crepository.findAll());
		return "activityList";
	}

	// RESTful service to get activities list
	@GetMapping(value = "/activities")
	public @ResponseBody List<Activity> activityListRest() {
		return (List<Activity>) arepository.findAll();
	}

	// RESTful service to get book by id
	@GetMapping(value = "/activity/{id}")
	public @ResponseBody Optional<Activity> findActivityRest(@PathVariable("id") Long activityId) {
		return arepository.findById(activityId);
	}

	// Map new activity model to addActivity.html
	@GetMapping(value = "/add")
	public String addActivity(Model model) {
		model.addAttribute("activity", new Activity());
		model.addAttribute("cities", crepository.findAll());
		model.addAttribute("city", new City());
		return "addActivity";
	}

	// Map form submit to save activity to repository then redirect to show
	// activityList.html
	@PostMapping(value = "/save")
	public String save(Activity activity) {
		arepository.save(activity);
		return "redirect:/activitylist";
	}

	// Add city from addActivity form
	@PostMapping(value = "/saveCity")
	public String saveCity(City city) {
		crepository.save(city);
		return "redirect:/add";
	}

	// Map delete by id then redirect to list
	@GetMapping(value = "/delete/{id}")
	@PreAuthorize("hasAuthority('ADMIN')")
	public String deleteActivity(@PathVariable("id") Long activityId, Model model) {
		arepository.deleteById(activityId);
		return "redirect:../activitylist";
	}

	// Map current book model to editBook.html
	@GetMapping(value = "/edit/{id}")
	public String editBook(@PathVariable("id") Long activityId, Model model) {
		model.addAttribute("activity", arepository.findById(activityId));
		model.addAttribute("cities", crepository.findAll());
		model.addAttribute("city", new City());
		return "editActivity";
	}

	@GetMapping(value = "/saveEdit")
	public String submitActivityEdit(@ModelAttribute Activity activity) {
		arepository.save(activity);
		return "redirect:/activitylist";
	}
}
