package com.rz.shoppingcart.dto;

import java.util.List;

import com.rz.core.shop.base.models.response.ResponseBody;

public class OrderListResponseModel extends ResponseBody{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5561618945147547482L;
	
	List<OrderDto> orderList;

	public List<OrderDto> getOrderList() {
		return orderList;
	}

	public void setOrderList(List<OrderDto> orderList) {
		this.orderList = orderList;
	}

}
