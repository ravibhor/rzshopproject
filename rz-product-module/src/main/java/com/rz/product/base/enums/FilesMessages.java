package com.rz.product.base.enums;

import com.rz.core.shop.base.enums.Messages;

public enum FilesMessages implements Messages {
	
	FILE_UPLOADED("file.file.upload"),
	FILE_NOT_FOUND("file.file.notfound"),
	FILE_DELETED("file.file.deleted"),
	FILE_DOWNLOAD("file.file.download"),
	FILE_SUCCESS("file.file.success"),
	FILE_ALREADY_EXISTS("file.file.already.exists");

	private String message;

	private FilesMessages(String message) {
		this.message = message;
	}

	@Override
	public String getMessage() {
		return message;
	}
}
