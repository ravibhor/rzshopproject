package com.rz.shoppingcart.dto;

import java.util.Date;
import java.util.List;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.rz.core.shop.common.model.Billing;
import com.rz.core.shop.common.model.Delivery;
import com.rz.shoppingcart.model.OrderStatus;
import com.rz.shoppingcart.model.OrderType;

public class ReadableOrderDto {
	
	private Long id;
	private long userId;
	private double totalPrice;
	List<OrderItemDto> orderItems;
	private OrderStatus orderStatus;
	@Enumerated(value = EnumType.STRING)
	private OrderType orderType = OrderType.ORDER;
	private Boolean confirmedAddress = false;
	private Delivery delivery = null;
	private Billing billing = null;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.sss'Z'")
	private Date createdAt;
	
	

}
