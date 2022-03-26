package com.rz.product.base.constants;

import com.rz.core.shop.base.constants.ErrorConstants;
import com.rz.core.shop.base.constants.HttpStatusCodes;
import com.rz.core.shop.base.enums.DefaultMessages;
import com.rz.core.shop.base.enums.Messages;
import com.rz.product.base.enums.ProductMessages;

public enum ProductErrorConstants implements ErrorConstants {

	BAD_REQUEST(DefaultMessages.BAD_REQUEST, HttpStatusCodes.CLIENT_ERROR_BAD_REQUEST),
	PRODUCT_NOT_FOUND(ProductMessages.PRODUCT_NOT_FOUND, HttpStatusCodes.CLIENT_ERROR_NOT_FOUND);

	private Messages message;
	private int statusCode;

	private ProductErrorConstants(Messages message, int statusCode) {
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
