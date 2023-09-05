package com.bezkoder.spring.datajpa.repository;

import java.util.List;

import com.bezkoder.spring.datajpa.model.Food;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FoodRepository extends JpaRepository<Food, Long> {

	Long findFoodById(long id);
	List<Food> findByUserId(Long user_id);
	List<Food> findByUser_Username(String username);
	List<Food> findByPublished(boolean published);
	List<Food> findByTitleContaining(String title);

	List<Food> findByTitleContainingAndUserId(String title, Long userId);


}
