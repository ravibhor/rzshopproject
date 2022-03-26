package com.rz.shoppingcart.base.constants;

import com.rz.core.shop.base.constants.ErrorConstants;
import com.rz.core.shop.base.constants.HttpStatusCodes;
import com.rz.core.shop.base.enums.DefaultMessages;
import com.rz.core.shop.base.enums.Messages;

public enum OrderErrorConstants implements ErrorConstants {
	BAD_REQUEST(DefaultMessages.BAD_REQUEST, HttpStatusCodes.CLIENT_ERROR_BAD_REQUEST);

	private Messages message;
	private int statusCode;;

	private OrderErrorConstants(Messages message, int statusCode) {
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
