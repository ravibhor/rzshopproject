package com.rz.shoppingcart.repositories;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.rz.shoppingcart.model.ShoppingCartItem;

@Repository
public interface ShoppingCartItemRepository extends JpaRepository<ShoppingCartItem, Long> {
	
	@Modifying
	@Transactional
	@Query("DELETE FROM ShoppingCartItem c where c.id = :cartItemId and c.userId=:userId")
	void deleteCartByIdAndUserId(@Param("cartItemId") Long cartItemId,@Param("userId") Long userId);
	
	@Modifying
	@Transactional
	@Query("DELETE FROM ShoppingCartItem c where c.userId=:userId")
	void deleteAllCartByUserId(@Param("userId") Long userId);
	
	@Query("FROM ShoppingCartItem c where c.userId=:userId")
	List<ShoppingCartItem> findByUserId(@Param("userId") Long userId);
	
	@Query("FROM ShoppingCartItem c WHERE c.product.productId= :productId and c.userId=:userId")
	Optional<ShoppingCartItem> getCartByProductIdAnduserId(@Param("productId")Long productId,@Param("userId")Long userId);
	
	@Modifying
    @Transactional
	@Query("update ShoppingCartItem c set c.quantity=:quantity,c.productPrice=:productPrice,c.total=:total WHERE c.id=:cart_id")
	void updateQtyByCartId(@Param("cart_id")Long cart_id,@Param("productPrice")double productPrice,@Param("quantity")long quantity,@Param("total") BigDecimal total);


}
