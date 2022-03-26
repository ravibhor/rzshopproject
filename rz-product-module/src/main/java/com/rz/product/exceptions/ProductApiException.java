package com.rz.product.exceptions;

import com.rz.core.shop.base.exceptions.APIException;
import com.rz.product.base.constants.ProductErrorConstants;

public class ProductApiException extends APIException {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final ProductErrorConstants errorConstant;
	
	public ProductApiException(ProductErrorConstants productErrorConstants) {
		super(productErrorConstants.getMessage().toString());
		this.errorConstant = productErrorConstants;
	}

	@Override
	public ProductErrorConstants getErrorConstant() {
		return errorConstant;
	}

}
