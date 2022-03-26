package com.rz.product.base.enums;

import com.rz.core.shop.base.enums.Messages;

public enum ProductMessages implements Messages {

	PRODUCT_CREATED("product.product.created"),
	PRODUCT_NOT_FOUND("product.product.notfound"),
	PRODUCT_DELETED("product.product.deleted"),
	PRODUCT_SUCCESS("product.product.success");

	private String message;

	private ProductMessages(String message) {
		this.message = message;
	}

	@Override
	public String getMessage() {
		return message;
	}

}
