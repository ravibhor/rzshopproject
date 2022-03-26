package com.rz.core.shop.base.constants;

import com.rz.core.shop.base.enums.Messages;
import com.rz.core.shop.base.enums.DefaultMessages;
import com.rz.core.shop.base.enums.UserMessages;

public enum UserErrorConstants implements ErrorConstants {

	BAD_REQUEST(DefaultMessages.BAD_REQUEST, HttpStatusCodes.CLIENT_ERROR_BAD_REQUEST),
	INVALID_CREDENTIALS(UserMessages.INVALID_CREDENTIALS, HttpStatusCodes.CLIENT_ERROR_BAD_REQUEST),
	EMAIL_NOT_VERIFIED(UserMessages.EMAIL_NOT_VERIFIED, HttpStatusCodes.CLIENT_ERROR_CONFLICT),
	EMAIL_ALREADY_EXIST(UserMessages.EMAIL_ALREADY_EXIST, HttpStatusCodes.CLIENT_ERROR_CONFLICT),
	USER_ALREADY_EXISTS(UserMessages.USER_ALREADY_EXISTS, HttpStatusCodes.CLIENT_ERROR_CONFLICT), 
	USERNAME_ALREADY_EXISTS(UserMessages.USERNAME_ALREADY_EXISTS, HttpStatusCodes.CLIENT_ERROR_CONFLICT), 
	USER_ROLES_EMPTY(UserMessages.USER_ROLES_EMPTY, HttpStatusCodes.CLIENT_ERROR_CONFLICT),
	ROLES_NOT_FOUND(UserMessages.ROLES_NOT_FOUND, HttpStatusCodes.CLIENT_ERROR_NOT_FOUND),
	ROLE_NOT_FOUND(UserMessages.ROLE_NOT_FOUND, HttpStatusCodes.CLIENT_ERROR_NOT_FOUND),
	ROLE_EXISTS(UserMessages.ROLE_EXISTS, HttpStatusCodes.DUPLICATE_RECORD_EXISTS),
	MODULE_ACCESS_NOT_FOUND(UserMessages.MODULE_ACCESS_NOT_FOUND, HttpStatusCodes.CLIENT_ERROR_NOT_FOUND),
	USER_NOT_FOUND(UserMessages.USER_NOT_FOUND, HttpStatusCodes.CLIENT_ERROR_NOT_FOUND),
	USER_UNAUTHORIZED(UserMessages.USER_UNAUTHORIZED,HttpStatusCodes.USER_ERROR_UNAUTHORIZED);

		
	private Messages message;
	private int statusCode;

	private UserErrorConstants(Messages message, int statusCode) {
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
