package com.rz.core.shop.base.enums;

public enum DefaultMessages implements Messages {

	EMPTY_MESSAGE("empty.message"),
	BAD_REQUEST("bad.request"),
	INTERNAL_SERVER_ERROR("internal.server.error"),
	UNABLE_TO_PARSE_REQUEST_BODY("unable.to.parse.request.body"),
	UNAUTHORIZED_USER("unauthorized.user"),
	SESSION_EXPIRED("session.expired"),
	INVALID_FILE_SIZE("invalid.file.size"),
	MISSING_X_TENANT_HEADER("missing.xtenantid");
	
	private String message;

	private DefaultMessages(String message) {
		this.message = message;
	}

	@Override
	public String getMessage() {
		return message;
	}
}
