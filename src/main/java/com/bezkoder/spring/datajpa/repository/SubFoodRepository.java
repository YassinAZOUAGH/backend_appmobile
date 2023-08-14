package com.bezkoder.spring.datajpa.repository;

import com.bezkoder.spring.datajpa.model.SubFood;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.Modifying;
import javax.transaction.Transactional;
import java.util.List;


public interface SubFoodRepository extends JpaRepository<SubFood, Long> {
	List<SubFood> findByPublished(boolean published);
	List<SubFood> findByTitleContaining(String title);

	@Query("select u from SubFood u where u.food =?1")
	List<SubFood> findByFoodContaining(long food);

	@Modifying
	@Transactional
	//@Query("DELETE FROM SubFood c WHERE c.food IN (SELECT food FROM SubFood GROUP BY food HAVING COUNT(food) > 1)")
	@Query("DELETE FROM SubFood c WHERE c.food = :foodValue")
	void deleteDuplicatesByFood(long foodValue);


	@Query("SELECT MAX(f.price) FROM SubFood f WHERE f.food = :foodValue")
	Double findMaxPrice(long foodValue);

	@Query("SELECT MIN(f.price) FROM SubFood f WHERE f.food = :foodValue")
	Double findMinPrice(long foodValue);


	/*@Query("SELECT f.food, MAX(f.price) FROM SubFood f GROUP BY f.food")
	List<Object[]> findMaxPricesByGroup();

	@Query("SELECT f.food, MIN(f.price) FROM SubFood f GROUP BY f.food")
	List<Object[]> findMinPricesByGroup();*/
}
