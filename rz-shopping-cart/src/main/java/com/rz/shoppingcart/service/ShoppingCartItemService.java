package com.rz.shoppingcart.service;

import java.util.List;

import com.rz.shoppingcart.dto.ShoppingCartItemDto;

public interface ShoppingCartItemService {

	ShoppingCartItemDto addToCartItem(ShoppingCartItemDto dto) throws Exception;

	ShoppingCartItemDto updateCartItem(ShoppingCartItemDto dto,long userId) throws Exception;

	ShoppingCartItemDto getCartById(long id) throws Exception;

	void deleteCartByIdAndUserId(long cartItemId, long userId) throws Exception;

	void deleteAllCartByUserId(long userId) throws Exception;

	List<ShoppingCartItemDto> getCartByUserId(long userId) throws Exception;
	
	void updateQtyByCartId(long cartId,long qty,double price) throws Exception;

}
