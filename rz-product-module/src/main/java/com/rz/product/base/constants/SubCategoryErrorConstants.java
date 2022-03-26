package com.rz.product.base.constants;

import com.rz.core.shop.base.constants.ErrorConstants;
import com.rz.core.shop.base.constants.HttpStatusCodes;
import com.rz.core.shop.base.enums.DefaultMessages;
import com.rz.core.shop.base.enums.Messages;
import com.rz.product.base.enums.SubCategoriesMessages;

public enum SubCategoryErrorConstants implements ErrorConstants {
	
	BAD_REQUEST(DefaultMessages.BAD_REQUEST, HttpStatusCodes.CLIENT_ERROR_BAD_REQUEST),
	SUB_CATEGORY_NOT_FOUND(SubCategoriesMessages.SUB_CATEGORY_NOT_FOUND, HttpStatusCodes.CLIENT_ERROR_NOT_FOUND),
	SUB_CATEGORY_ALREADY_EXISTS(SubCategoriesMessages.SUB_CATEGORY_CREATED, HttpStatusCodes.ALREADY_EXISTS),
	SUB_CATEGORY_CREATED(SubCategoriesMessages.SUB_CATEGORY_CREATED,HttpStatusCodes.SUCCESS_CREATED),
	SUB_CATEGORY_DELETED(SubCategoriesMessages.SUB_CATEGORY_DELETED,HttpStatusCodes.SUCCESS_OK);
	
	
	private Messages message;
	private int statusCode;
	
	private SubCategoryErrorConstants(Messages message, int statusCode) {
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
