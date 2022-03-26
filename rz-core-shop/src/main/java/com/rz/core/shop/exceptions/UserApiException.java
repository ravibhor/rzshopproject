package com.rz.core.shop.exceptions;

import com.rz.core.shop.base.constants.UserErrorConstants;
import com.rz.core.shop.base.exceptions.APIException;

public class UserApiException extends APIException {
	
	private static final long serialVersionUID = -5304621011601779424L;
	private final UserErrorConstants errorConstant;

	public UserApiException(UserErrorConstants userErrorConstant) {
		super(userErrorConstant.getMessage().toString());
		this.errorConstant = userErrorConstant;
	}

	@Override
	public UserErrorConstants getErrorConstant() {
		return errorConstant;
	}
}