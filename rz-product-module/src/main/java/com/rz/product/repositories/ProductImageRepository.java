package com.rz.product.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.rz.product.model.ProductImage;

@Repository
public interface ProductImageRepository extends JpaRepository<ProductImage, Long> {

	ProductImage findByProductImgName(String productImgName);

	@Query("FROM ProductImage p where p.product.productId = :productId and p.product.status='ACTIVE'")
	List<ProductImage> findByProductId(@Param("productId") Long productId);

	@Query("FROM ProductImage p where p.status = 'ACTIVE'")
	List<ProductImage> findAll();

	@Query("FROM ProductImage p where  p.id = :id and p.status = 'ACTIVE'")
	Optional<ProductImage> findById(@Param("id") Long id);

}
