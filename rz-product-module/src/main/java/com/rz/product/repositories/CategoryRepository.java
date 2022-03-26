package com.rz.product.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.rz.product.model.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long>{

	@Query("FROM Category c where  c.categoryTitle = :categoryTitle and c.status = 'ACTIVE'")
	Optional<Category> findByCategorytitle(@Param("categoryTitle") String categoryTitle);
	
	@Query("FROM Category c where  c.categoryId = :categoryId and c.status = 'ACTIVE'")
	Optional<Category> findById(@Param("categoryId") Long categoryId);
	
	@Query("FROM Category c where c.status = 'ACTIVE'")
	List<Category> findAll();

}
