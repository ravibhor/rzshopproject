package com.rz.shoppingcart.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rz.shoppingcart.model.ShoppingCartItem;

@Repository
public interface CartItemRepository extends JpaRepository<ShoppingCartItem, Long>{

}
