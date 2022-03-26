package com.rz.product.base.enums;

import com.rz.core.shop.base.enums.Messages;

public enum CategoriesMessages implements Messages {

	CATEGORY_CREATED("category.category.created"),
	CATEGORY_NOT_FOUND("category.category.categorynotfound"),
	CATEGORY_DELETED("category.category.deleted"),
	CATEGORY_SUCCESS("category.category.success"),
	CATEGORY_ALREADY_EXISTS("category.category.already.exists");
	
	private String message;

	private CategoriesMessages(String message) {
		this.message = message;
	}

	@Override
	public String getMessage() {
		return message;
	}
}
