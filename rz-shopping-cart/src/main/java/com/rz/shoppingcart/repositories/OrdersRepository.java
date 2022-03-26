package com.rz.shoppingcart.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rz.shoppingcart.model.Order;

@Repository
public interface OrdersRepository extends JpaRepository<Order, Long>{

}
