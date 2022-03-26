package com.rz.shoppingcart.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rz.core.shop.base.BaseController;
import com.rz.core.shop.base.constants.HttpStatusCodes;
import com.rz.core.shop.base.models.response.Response;
import com.rz.shoppingcart.base.enums.CartMessages;
import com.rz.shoppingcart.dto.ShoppingCartDto;
import com.rz.shoppingcart.dto.ShoppingCartItemDto;
import com.rz.shoppingcart.service.ShoppingCartService;
import com.rz.shoppingcart.services.impl.ShoppingCartServiceImpl;

@RestController
@RequestMapping(value = "/api/v1")
@SuppressWarnings({ "unused" })
public class ShoppingCartApi extends BaseController {
	
	@Autowired
	private ShoppingCartServiceImpl cartService;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ShoppingCartApi.class);
	
	@PostMapping(value = "/shoppingcart", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Response> addCategory(@RequestBody ShoppingCartDto cartDto) throws Exception {
		ShoppingCartDto dto = new ShoppingCartDto();
		dto = cartService.addToCart(cartDto);
		LOGGER.info("Cart added success...");
		return createResponse(HttpStatusCodes.SUCCESS_CREATED, CartMessages.SHOPPING_CART_ADDED_SUCCESS, null, 0, dto);
	}
	
	@GetMapping(value = "/shoppingcart/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Response> getCartByUserId(@PathVariable long id) throws Exception {
		ShoppingCartDto dto=cartService.getCartById(id);
		LOGGER.info("Get category data...");
		return createResponse(HttpStatusCodes.SUCCESS_OK, 0, dto);
	}


}
