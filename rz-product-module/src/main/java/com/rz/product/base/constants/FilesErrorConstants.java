package com.rz.product.base.constants;

import com.rz.core.shop.base.constants.ErrorConstants;
import com.rz.core.shop.base.constants.HttpStatusCodes;
import com.rz.core.shop.base.enums.DefaultMessages;
import com.rz.core.shop.base.enums.Messages;
import com.rz.product.base.enums.FilesMessages;

public enum FilesErrorConstants implements ErrorConstants {



	BAD_REQUEST(DefaultMessages.BAD_REQUEST, HttpStatusCodes.CLIENT_ERROR_BAD_REQUEST),
	FILE_NOT_FOUND(FilesMessages.FILE_NOT_FOUND, HttpStatusCodes.CLIENT_ERROR_NOT_FOUND),
	FILE_DOWNLOAD(FilesMessages.FILE_DOWNLOAD, HttpStatusCodes.SUCCESS_OK),
	FILE_SUCCESS(FilesMessages.FILE_SUCCESS, HttpStatusCodes.SUCCESS_OK),
	FILE_UPLOADED(FilesMessages.FILE_UPLOADED,HttpStatusCodes.SUCCESS_OK),
	FILE_ALREADY_EXISTS(FilesMessages.FILE_ALREADY_EXISTS,HttpStatusCodes.ALREADY_EXISTS);

	private Messages message;
	private int statusCode;

	private FilesErrorConstants(Messages message, int statusCode) {
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
