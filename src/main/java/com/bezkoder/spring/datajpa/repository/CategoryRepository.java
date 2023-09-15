package com.bezkoder.spring.datajpa.repository;

import com.bezkoder.spring.datajpa.model.Category;
import com.bezkoder.spring.datajpa.model.Food;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Long> {
	List<Category> findByPublished(boolean published);
	List<Category> findByTitleContaining(String title);
	List<Category> findByUserId(Long user_id);
}
