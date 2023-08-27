package com.bezkoder.spring.datajpa.controller;

import com.bezkoder.spring.datajpa.model.Food;
import com.bezkoder.spring.datajpa.repository.FoodRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

//@CrossOrigin(origins = "http://192.168.0.246:4200")
@CrossOrigin(origins = "https://smarteasyorders.com")
@RestController
@RequestMapping("/api/admin")
public class FoodAdminController {
	@Autowired
	FoodRepository foodRepository;

	@GetMapping("/foods")
	public ResponseEntity<List<Food>> getAllFoods(@RequestParam(required = false) String title) {
		try {
			List<Food> foods = new ArrayList<Food>();

			if (title == null)
				foodRepository.findAll().forEach(foods::add);
			else
				foodRepository.findByTitleContaining(title).forEach(foods::add);

			if (foods.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}

			return new ResponseEntity<>(foods, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/foods/{id}")
	public ResponseEntity<Food> getFoodById(@PathVariable("id") long id) {
		Optional<Food> foodData = foodRepository.findById(id);

		if (foodData.isPresent()) {
			return new ResponseEntity<>(foodData.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping("/foods")
	public ResponseEntity<Food> createFood(@RequestBody Food food) {
		try {
			Food _food = foodRepository
					.save(new Food(
							food.getTitle(),
							food.getPrice(),
							food.getImage(),
							food.getDescription(),
							food.isPublished()
					));
			return new ResponseEntity<>(_food, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PutMapping("/foods/{id}")
	public ResponseEntity<Food> updateFood(@PathVariable("id") long id, @RequestBody Food food) {
		Optional<Food> foodData = foodRepository.findById(id);

		if (foodData.isPresent()) {
			Food _food = foodData.get();
			_food.setTitle(food.getTitle());
			_food.setPrice(food.getPrice());
			_food.setImage(food.getImage());
			_food.setDescription(food.getDescription());
			_food.setPublished(food.isPublished());
			return new ResponseEntity<>(foodRepository.save(_food), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@DeleteMapping("/foods/{id}")
	public ResponseEntity<HttpStatus> deleteFood(@PathVariable("id") long id) {
		try {
			foodRepository.deleteById(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@DeleteMapping("/foods")
	public ResponseEntity<HttpStatus> deleteAllFoods() {
		try {
			foodRepository.deleteAll();
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@GetMapping("/foods/published")
	public ResponseEntity<List<Food>> findByPublished() {
		try {
			List<Food> foods = foodRepository.findByPublished(true);
			//foodRepository.findByPublished(true).forEach(foods::add);
			//System.out.println(foods);
			if (foods.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			return new ResponseEntity<>(foods, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}