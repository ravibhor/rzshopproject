package com.rz.product.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.rz.product.model.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

	@Query("FROM Product p where p.status = 'ACTIVE'")
	List<Product> findAll();

	@Query("FROM Product p where  p.productId = :productId and p.status = 'ACTIVE'")
	Optional<Product> findById(@Param("productId") Long productId);
}
