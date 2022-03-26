package com.rz.shoppingcart.services.impl;

import java.util.Date;
import java.util.Optional;
import java.util.UUID;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.rz.core.shop.base.constants.UserErrorConstants;
import com.rz.core.shop.exceptions.UserApiException;
import com.rz.core.shop.user.services.CustomUserDetailsService;
import com.rz.shoppingcart.base.constants.CartErrorConstants;
import com.rz.shoppingcart.dto.ShoppingCartDto;
import com.rz.shoppingcart.dto.ShoppingCartItemDto;
import com.rz.shoppingcart.exceptions.ShoppingCartApiException;
import com.rz.shoppingcart.model.ShoppingCart;
import com.rz.shoppingcart.model.ShoppingCartItem;
import com.rz.shoppingcart.repositories.ShoppingCartRepository;
import com.rz.shoppingcart.service.ShoppingCartItemService;
import com.rz.shoppingcart.service.ShoppingCartService;

@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {
	
	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private CustomUserDetailsService userDetailService;
	
	@Autowired
	private ShoppingCartRepository cartRepository;

	@Override
	public ShoppingCartDto addToCart(ShoppingCartDto dto) throws Exception {

		// check whether user login or not
		String authenticateUser = userDetailService.authenticatedUser();
		if (authenticateUser == null) {
			throw new UserApiException(UserErrorConstants.USER_UNAUTHORIZED);
		}

		long userId = Long.parseLong(authenticateUser);
		ShoppingCart cartModel = new ShoppingCart();
		cartModel.setCustomerId(userId);
		cartModel.setOrderId(dto.getOrderId());
		cartModel.setShoppingCartCode(uniqueShoppingCartCode());
		cartModel.setPromoCode(dto.getPromoCode());
		cartModel.setPromoAdded(new Date());
		cartRepository.save(cartModel);
		ShoppingCartDto cartDto=modelMapper.map(cartModel, ShoppingCartDto.class);
		return cartDto;
	}

	@Override
	public ShoppingCartDto updateCart(ShoppingCartDto dto, long id) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ShoppingCartDto displayShoppingCart(ShoppingCartDto dto) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	private String uniqueShoppingCartCode() {
		return UUID.randomUUID().toString().replaceAll("-", "");
	}

	@Override
	public ShoppingCartDto getCartById(long id) throws Exception {
		Optional<ShoppingCart> cartItem = Optional.ofNullable(cartRepository.findById(id)
				.orElseThrow(() -> new ShoppingCartApiException(CartErrorConstants.SHOPPING_CART_NOT_FOUND)));
		ShoppingCartDto dto = modelMapper.map(cartItem.get(), ShoppingCartDto.class);
		return dto;
	}

}
