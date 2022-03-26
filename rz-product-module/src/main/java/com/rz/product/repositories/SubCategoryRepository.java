package com.rz.product.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.rz.product.model.SubCategory;

@Repository
public interface SubCategoryRepository extends JpaRepository<SubCategory, Long>{

	@Query("FROM SubCategory c where  c.subCategoryId = :subCategoryId and c.status = 'ACTIVE'")
	Optional<SubCategory> findBySubCategoryId(@Param("subCategoryId") Long subCategoryId);
	
	@Query("FROM SubCategory c where c.status = 'ACTIVE'")
	List<SubCategory> findAll();
	
	@Query("FROM SubCategory c where c.category.categoryId = :categoryId and c.status='ACTIVE'")
	List<SubCategory> findByCategoryId(@Param("categoryId") Long categoryId);
}
