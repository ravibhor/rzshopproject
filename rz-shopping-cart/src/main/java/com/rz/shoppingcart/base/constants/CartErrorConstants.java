package com.rz.shoppingcart.base.constants;

import com.rz.core.shop.base.constants.ErrorConstants;
import com.rz.core.shop.base.constants.HttpStatusCodes;
import com.rz.core.shop.base.enums.DefaultMessages;
import com.rz.core.shop.base.enums.Messages;
import com.rz.shoppingcart.base.enums.CartMessages;

public enum CartErrorConstants implements ErrorConstants {

	BAD_REQUEST(DefaultMessages.BAD_REQUEST, HttpStatusCodes.CLIENT_ERROR_BAD_REQUEST),
	SHOPPING_CART_NOT_FOUND(CartMessages.SHOPPING_CART_NOT_FOUND, HttpStatusCodes.CLIENT_ERROR_NOT_FOUND),
	SHOPPING_CART_ADDED_SUCCESS(CartMessages.SHOPPING_CART_ADDED_SUCCESS, HttpStatusCodes.SUCCESS_CREATED),
	SHOPPING_CART_CREATED(CartMessages.SHOPPING_CART_CREATED, HttpStatusCodes.SUCCESS_CREATED),
	SHOPPING_CART_DELETED(CartMessages.SHOPPING_CART_DELETED, HttpStatusCodes.SUCCESS_OK),
	SHOPPING_CART_UPDATED(CartMessages.SHOPPING_CART_DELETED, HttpStatusCodes.SUCCESS_OK),
	SHOPPING_CART_ITEM_ALREADY_EXISTS(CartMessages.SHOPPING_CART_ITEM_ALREADY_EXISTS, HttpStatusCodes.ALREADY_EXISTS);


	private Messages message;
	private int statusCode;

	private CartErrorConstants(Messages message, int statusCode) {
		this.message = message;
		this.statusCode = statusCode;
	}

	@Override
	public Messages getMessage() {
		return message;
	}

	@Override
	public int getStatusCode() {
		return statusCode;
	}

}
