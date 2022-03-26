package com.rz.shoppingcart.base.enums;

import com.rz.core.shop.base.enums.Messages;

public enum OrderMessages implements Messages{
	
	ORDERT_PLACE_SUCCESS("order.order.success"),
	ORDER__SUCCESS("order.order.success");
	
	private String message;

	private OrderMessages(String message) {
		this.message = message;
	}

	@Override
	public String getMessage() {
		return message;
	}
	
	

}
