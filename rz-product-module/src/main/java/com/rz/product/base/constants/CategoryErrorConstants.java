package com.rz.product.base.constants;

import com.rz.core.shop.base.enums.Messages;
import com.rz.core.shop.base.constants.ErrorConstants;
import com.rz.core.shop.base.constants.HttpStatusCodes;
import com.rz.core.shop.base.enums.DefaultMessages;
import com.rz.product.base.enums.CategoriesMessages;

public enum CategoryErrorConstants implements ErrorConstants {

	BAD_REQUEST(DefaultMessages.BAD_REQUEST, HttpStatusCodes.CLIENT_ERROR_BAD_REQUEST),
	CATEGORY_NOT_FOUND(CategoriesMessages.CATEGORY_NOT_FOUND, HttpStatusCodes.CLIENT_ERROR_NOT_FOUND),
	CATEGORY_ALREADY_EXISTS(CategoriesMessages.CATEGORY_ALREADY_EXISTS, HttpStatusCodes.ALREADY_EXISTS);
	
		
	private Messages message;
	private int statusCode;

	private CategoryErrorConstants(Messages message, int statusCode) {
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
