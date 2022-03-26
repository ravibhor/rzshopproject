package com.rz.core.shop.base.enums;

public enum UserMessages implements Messages {

	INVALID_CREDENTIALS("user.invalid.credentials"),
	EMAIL_NOT_VERIFIED("user.email.not.verified"),
	EMAIL_ALREADY_EXIST("user.email.already.exist"),
	USER_ALREADY_EXISTS("user.user.already.exists"),
	USERNAME_ALREADY_EXISTS("user.username.already.exists"),
	USER_ROLES_EMPTY("user.user.roles.empty"), 
	ROLE_CREATED("user.user.role.created"), 
	ROLE_UPDATED("user.user.role.updated"), 
	ROLES_NOT_FOUND("user.user.roles.notfound"), 
	ROLE_NOT_FOUND("user.user.role.notfound"), 
	ROLE_EXISTS("user.user.role.exists"), 
	MODULE_ACCESS_NOT_FOUND("user.moduleaccess.notfound"),
	USER_CREATED("user.user.created"),
	LOGIN_SUCCESS("user.login.success"),
	SUCCESS("user.success"),
	USER_UNAUTHORIZED("user.user.unauthorized"),
	USER_NOT_FOUND("user.user.usernotfound");
	
	private String message;

	private UserMessages(String message) {
		this.message = message;
	}

	@Override
	public String getMessage() {
		return message;
	}
}
