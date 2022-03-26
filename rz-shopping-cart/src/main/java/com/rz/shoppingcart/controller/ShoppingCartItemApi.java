package com.rz.shoppingcart.controller;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rz.core.shop.base.BaseController;
import com.rz.core.shop.base.constants.HttpStatusCodes;
import com.rz.core.shop.base.models.response.Response;
import com.rz.shoppingcart.base.enums.CartMessages;
import com.rz.shoppingcart.dto.ShoppingCartItemDto;
import com.rz.shoppingcart.dto.ShoppingCartItemList;
import com.rz.shoppingcart.service.ShoppingCartItemService;
import com.rz.shoppingcart.services.impl.ShoppingCartItemServiceImpl;

@RestController
@RequestMapping(value = "/api/v1")
@SuppressWarnings({ "unused" })
public class ShoppingCartItemApi extends BaseController {
	private static final Logger LOGGER = LoggerFactory.getLogger(ShoppingCartItemApi.class);

	@Autowired
	private ShoppingCartItemServiceImpl cartItemService;

	@PostMapping(value = "/shoppingcartitem", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Response> addCategory(@RequestBody ShoppingCartItemDto cartItemDto) throws Exception {
		ShoppingCartItemDto dto = new ShoppingCartItemDto();
		dto = cartItemService.addToCartItem(cartItemDto);
		LOGGER.info("Cart item added success...");
		return createResponse(HttpStatusCodes.SUCCESS_CREATED, CartMessages.SHOPPING_CART_ADDED_SUCCESS, null, 0, dto);
	}

	@GetMapping(value = "/shoppingcartitem/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Response> getCartItemById(@PathVariable long id) throws Exception {
		ShoppingCartItemDto dto = cartItemService.getCartById(id);
		LOGGER.info("Get category data...");
		return createResponse(HttpStatusCodes.SUCCESS_OK, 0, dto);
	}

	@GetMapping(value = "/shoppingcartitem/user/{userId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Response> getCartByUserId(@PathVariable long userId) throws Exception {
		List<ShoppingCartItemDto> list = cartItemService.getCartByUserId(userId);
		ShoppingCartItemList response = new ShoppingCartItemList();
		response.setShoppingCartItemList(list);
		LOGGER.info("Get category data...");
		return createResponse(HttpStatusCodes.SUCCESS_OK, 0, response);
	}

	@DeleteMapping(value = "/shoppingcartitem/user/{userId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Response> deleteCartByUserId(@PathVariable long userId) throws Exception {
		cartItemService.deleteAllCartByUserId(userId);
		LOGGER.info("Cart item deleted...");
		return createResponse(HttpStatusCodes.SUCCESS_OK, CartMessages.SHOPPING_CART_DELETED, null, 0);
	}

	@DeleteMapping(value = "/shoppingcartitem/{id}/user/{userId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Response> deleteCartByIdAndUserId(@PathVariable long id, @PathVariable long userId)
			throws Exception {
		cartItemService.deleteCartByIdAndUserId(id, userId);
		LOGGER.info("Cart item deleted...");
		return createResponse(HttpStatusCodes.SUCCESS_OK, CartMessages.SHOPPING_CART_DELETED, null, 0);
	}

	@PutMapping(value = "/shoppingcartitem/{userId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Response> updateCartItem(@RequestBody List<ShoppingCartItemDto> cartItemDtoList, @PathVariable long userId)
			throws Exception {
		List<ShoppingCartItemDto> list=new ArrayList<ShoppingCartItemDto>();
		ShoppingCartItemList response = new ShoppingCartItemList();
		for (ShoppingCartItemDto cartItemDto : cartItemDtoList) {
			ShoppingCartItemDto itemDto = cartItemService.updateCartItem(cartItemDto, userId);	
			list.add(itemDto);
			response.setShoppingCartItemList(list);
		}
		LOGGER.info("Shopping cart item updated...");
		return createResponse(HttpStatusCodes.SUCCESS_OK, CartMessages.SHOPPING_CART_UPDATED, null, 0, response);
	}

	@PutMapping(value = "/shoppingcartitem/update/{cartId}/{qty}/{price}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Response> updateCartItem(@RequestBody @PathVariable long cartId,@PathVariable long qty,@PathVariable double price)
			throws Exception {
		cartItemService.updateQtyByCartId(cartId, qty, price);
		LOGGER.info("Shopping cart item updated...");
		return createResponse(HttpStatusCodes.SUCCESS_OK, CartMessages.SHOPPING_CART_UPDATED, null,0);
	}
}
