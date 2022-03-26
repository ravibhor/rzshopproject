package com.rz.shoppingcart.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rz.core.shop.base.BaseController;
import com.rz.core.shop.base.constants.HttpStatusCodes;
import com.rz.core.shop.base.constants.UserErrorConstants;
import com.rz.core.shop.base.models.response.Response;
import com.rz.core.shop.exceptions.UserApiException;
import com.rz.core.shop.user.services.CustomUserDetailsService;
import com.rz.shoppingcart.base.enums.CartMessages;
import com.rz.shoppingcart.base.enums.OrderMessages;
import com.rz.shoppingcart.dto.OrderDto;
import com.rz.shoppingcart.dto.OrderListResponseModel;
import com.rz.shoppingcart.dto.ShoppingCartDto;
import com.rz.shoppingcart.service.OrderService;
import com.rz.shoppingcart.services.impl.ShoppingCartServiceImpl;

@RestController
@RequestMapping(value = "/api/v1")
@SuppressWarnings({ "unused" })
public class OrderApi extends BaseController {

	@Autowired
	private OrderService orderService;

	@Autowired
	private CustomUserDetailsService userDetailService;

	private static final Logger LOGGER = LoggerFactory.getLogger(OrderApi.class);

	@PostMapping(value = "/order/{userId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Response> addCategory() throws Exception {
		// check whether user login or not

		String authenticateUser = userDetailService.authenticatedUser();
		if (authenticateUser == null) {
			throw new UserApiException(UserErrorConstants.USER_UNAUTHORIZED);
		}

		orderService.placeOrder(Long.valueOf(authenticateUser));
		LOGGER.info("Order success...");
		return createResponse(HttpStatusCodes.SUCCESS_CREATED, OrderMessages.ORDERT_PLACE_SUCCESS, null, 0);
	}

	@GetMapping(value = "/order/user/{userId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Response> getOrdersByUserId(@PathVariable long userId) throws Exception {
		List<OrderDto> list = orderService.getOrdersByUserId(userId);
		OrderListResponseModel response = new OrderListResponseModel();
		response.setOrderList(list);
		LOGGER.info("Get category data...");
		return createResponse(HttpStatusCodes.SUCCESS_OK, 0, response);
	}

}
