package com.rz.shoppingcart.service;

import java.math.BigDecimal;
import java.util.List;

import com.rz.shoppingcart.dto.OrderDto;
import com.rz.shoppingcart.model.Order;

public interface OrderService {

	public void placeOrder(long userId) throws Exception;

	public Long saveOrder(Order order, long userId) throws Exception;
	
	public List<OrderDto> getOrdersByUserId(long userId)throws Exception;

	void updateOrder(long orderId, BigDecimal totalPrice) throws Exception;
}
