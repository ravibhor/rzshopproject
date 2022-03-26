package com.rz.shoppingcart.service;

import com.rz.shoppingcart.dto.ShoppingCartDto;

public interface ShoppingCartService {
	
	ShoppingCartDto addToCart(ShoppingCartDto dto) throws Exception;
	
	ShoppingCartDto updateCart(ShoppingCartDto dto,long id) throws Exception;
	
	ShoppingCartDto displayShoppingCart(ShoppingCartDto dto) throws Exception;
	
	ShoppingCartDto getCartById(long id) throws Exception;

}
