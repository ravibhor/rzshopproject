package com.rz.core.shop.base.exceptions;

import com.rz.core.shop.base.constants.ErrorConstants;

public abstract class APIException extends RuntimeException {
	
	private static final long serialVersionUID = 5489182194743688346L;

	public APIException(String message) {
		super(message);
	}
	
	public abstract ErrorConstants getErrorConstant();
}
