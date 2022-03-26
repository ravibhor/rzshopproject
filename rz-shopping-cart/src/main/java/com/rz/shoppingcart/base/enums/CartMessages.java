package com.rz.shoppingcart.base.enums;

import com.rz.core.shop.base.enums.Messages;

public enum CartMessages implements Messages {

	SHOPPING_CART_CREATED("shopping.cart.item.created"),
	SHOPPING_CART_NOT_FOUND("shopping.cart.item.notfound"),
	SHOPPING_CART_DELETED("shopping.cart.item.deleted"),
	SHOPPING_CART_ADDED_SUCCESS("shopping.cart.item.success"),
	SHOPPING_CART_UPDATED("shopping.cart.item.updated"),
	SHOPPING_CART_ITEM_ALREADY_EXISTS("shopping.cart.item.already.exists");

	private String message;

	private CartMessages(String message) {
		this.message = message;
	}

	@Override
	public String getMessage() {
		return message;
	}

}
