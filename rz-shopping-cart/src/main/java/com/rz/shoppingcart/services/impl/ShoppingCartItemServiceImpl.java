package com.rz.shoppingcart.services.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rz.core.shop.base.constants.UserErrorConstants;
import com.rz.core.shop.exceptions.UserApiException;
import com.rz.core.shop.user.services.CustomUserDetailsService;
import com.rz.product.base.constants.SubCategoryErrorConstants;
import com.rz.product.dto.ProductDto;
import com.rz.product.dto.ProductImageDto;
import com.rz.product.exceptions.SubCategoryApiException;
import com.rz.product.model.Product;
import com.rz.product.services.impl.ProductServiceImpl;
import com.rz.shoppingcart.base.constants.CartErrorConstants;
import com.rz.shoppingcart.dto.ShoppingCartItemDto;
import com.rz.shoppingcart.exceptions.ShoppingCartApiException;
import com.rz.shoppingcart.model.ShoppingCartItem;
import com.rz.shoppingcart.repositories.ShoppingCartItemRepository;
import com.rz.shoppingcart.repositories.ShoppingCartRepository;
import com.rz.shoppingcart.service.ShoppingCartItemService;

@Service
public class ShoppingCartItemServiceImpl implements ShoppingCartItemService {

	@Autowired
	private ShoppingCartItemRepository cartItemRepository;

	@Autowired
	private ShoppingCartRepository cartRepository;

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private CustomUserDetailsService userDetailService;

	@Autowired
	private ProductServiceImpl productService;

	@Autowired
	private ShoppingCartServiceImpl cartService;

	@Autowired
	private ShoppingCartItemServiceImpl cartItemSevice;

	@Override
	public ShoppingCartItemDto addToCartItem(ShoppingCartItemDto dto) throws Exception {
		// check whether user login or not
		/*
		 * String authenticateUser = userDetailService.authenticatedUser(); if
		 * (authenticateUser == null) { throw new
		 * UserApiException(UserErrorConstants.USER_UNAUTHORIZED); }
		 * 
		 * long userId = Long.parseLong(authenticateUser);
		 */
		Optional<ShoppingCartItem> item = cartItemRepository.getCartByProductIdAnduserId(dto.getProductId(),
				dto.getUserId());
		if (item.isPresent()) {
			throw new ShoppingCartApiException(CartErrorConstants.SHOPPING_CART_ITEM_ALREADY_EXISTS);
		}
		ProductDto productDto = productService.getProduct(dto.getProductId());
		Product product = new Product();
		product.setProductId(productDto.getProductId());
		int productCode = (int) productDto.getProductCode();

		ShoppingCartItem cartItem = new ShoppingCartItem();
		cartItem.setProduct(product);
		cartItem.setQuantity(dto.getQuantity());
		cartItem.setProductPrice(dto.getProductPrice());
		cartItem.setProductCode(productCode);
		cartItem.setUserId(dto.getUserId());
		cartItem.setTotal(cartItem.calculateTotal());
		cartItem = cartItemRepository.save(cartItem);

		ShoppingCartItemDto cartItemDto = modelMapper.map(cartItem, ShoppingCartItemDto.class);
		return cartItemDto;
	}

	@Transactional
	@Override
	public ShoppingCartItemDto updateCartItem(ShoppingCartItemDto dto, long userId) throws Exception {
		// check whether user login or not
		String authenticateUser = userDetailService.authenticatedUser();
		if (authenticateUser == null) {
			throw new UserApiException(UserErrorConstants.USER_UNAUTHORIZED);
		}

		Optional<ShoppingCartItem> cart = cartItemRepository.findById(dto.getId());

		long loginUser = Long.parseLong(authenticateUser);
		ShoppingCartItem cartItem = modelMapper.map(dto, ShoppingCartItem.class);
		cartItem.setId(cart.get().getId());
		dto.setUserId(loginUser);
		cartItem.setUpdatedBy(String.valueOf(loginUser));
		cartItem.setTotal(cartItem.calculateTotal());
		cartItem = cartItemRepository.save(cartItem);
		ShoppingCartItemDto cartDto = modelMapper.map(cartItem, ShoppingCartItemDto.class);
		return cartDto;
	}

	@Override
	public void deleteCartByIdAndUserId(long cartItemId, long userId) throws Exception {
		cartItemRepository.deleteCartByIdAndUserId(cartItemId, userId);
	}

	@Override
	public void deleteAllCartByUserId(long userId) throws Exception {
		cartItemRepository.deleteAllCartByUserId(userId);
	}

	@Override
	public List<ShoppingCartItemDto> getCartByUserId(long userId) throws Exception {

		List<ShoppingCartItem> cartItem = cartItemRepository.findByUserId(userId);
		List<ShoppingCartItemDto> dtoList = new ArrayList<ShoppingCartItemDto>();

		for (ShoppingCartItem cart : cartItem) {
			ShoppingCartItemDto dto = modelMapper.map(cart, ShoppingCartItemDto.class);
			dtoList.add(dto);
		}

		return dtoList;
	}

	@Override
	public ShoppingCartItemDto getCartById(long id) throws Exception {
		Optional<ShoppingCartItem> cartItem = Optional.ofNullable(cartItemRepository.findById(id)
				.orElseThrow(() -> new ShoppingCartApiException(CartErrorConstants.SHOPPING_CART_NOT_FOUND)));
		ShoppingCartItemDto dto = modelMapper.map(cartItem.get(), ShoppingCartItemDto.class);
		return dto;
	}

	@Override
	public void updateQtyByCartId(long cartId, long qty, double productPrice) throws Exception {

		ShoppingCartItem cartItem=new ShoppingCartItem();
		cartItem.setQuantity(qty);
		cartItem.setProductPrice(productPrice);
		BigDecimal total=cartItem.calculateTotal();
		cartItemRepository.updateQtyByCartId(cartId, productPrice, qty,total);

	}

}
