package com.rz.product.base.enums;

import com.rz.core.shop.base.enums.Messages;

public enum SubCategoriesMessages implements Messages {

	SUB_CATEGORY_CREATED("subcategory.subcategory.created"),
	SUB_CATEGORY_NOT_FOUND("subcategory.subcategory.notfound"),
	SUB_CATEGORY_DELETED("subcategory.subcategory.deleted"),
	SUB_CATEGORY_SUCCESS("subcategory.subcategory.success"),
	SUB_CATEGORY_ALREADY_EXISTS("subcategory.subcategory.already.exists");
	
	private String message;

	private SubCategoriesMessages(String message) {
		this.message = message;
	}

	@Override
	public String getMessage() {
		return message;
	}

}
