package com.rz.shoppingcart.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.rz.shoppingcart.model.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

	List<Order> findAllByUserIdOrderByCreatedAtDesc(long userId);

	@Modifying
	@Transactional
	@Query("update Order o set o.totalPrice=:totalPrice WHERE o.id=:orderId")
	void updateTotalPrice(@Param("orderId") long orderId,@Param("totalPrice") double totalPrice);
	
	@Query("FROM Order o WHERE o.user.id=:userId")
	Order findByUserId(@Param("userId") long userId);


}
