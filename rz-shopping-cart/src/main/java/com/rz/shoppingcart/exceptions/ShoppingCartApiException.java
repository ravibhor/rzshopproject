package com.rz.shoppingcart.exceptions;

import com.rz.core.shop.base.exceptions.APIException;
import com.rz.shoppingcart.base.constants.CartErrorConstants;

public class ShoppingCartApiException extends APIException{

	private static final long serialVersionUID = -5304621011601779424L;
	private final CartErrorConstants errorConstant;
	
	public ShoppingCartApiException(CartErrorConstants cartErrorConstant) {
		super(cartErrorConstant.getMessage().toString());
		this.errorConstant = cartErrorConstant;
	}
	@Override
	public CartErrorConstants getErrorConstant() {
		return errorConstant;
	}

}
