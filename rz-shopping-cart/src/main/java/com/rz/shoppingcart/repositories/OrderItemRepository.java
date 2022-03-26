package com.rz.shoppingcart.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.rz.shoppingcart.model.OrderItem;

@Repository
public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
	
	@Query("FROM OrderItem o where o.product.productId = :productId")
	OrderItem findByProductId(@Param("productId")long productId);
	
	@Modifying
	@Transactional
	@Query("update OrderItem o set o.productPrice = :productPrice,o.quantity = :quantity WHERE o.product.productId = :productId")
	void updateOrderItem(@Param("quantity") long quantity,@Param("productPrice") double productPrice,@Param("productId") long productId);

}
