package com.rz.product.exceptions;

import com.rz.core.shop.base.exceptions.APIException;
import com.rz.product.base.constants.CategoryErrorConstants;

public class CategoryApiException extends APIException {
	
	private static final long serialVersionUID = -5304621011601779424L;
	private final CategoryErrorConstants errorConstant;

	public CategoryApiException(CategoryErrorConstants categoryErrorConstant) {
		super(categoryErrorConstant.getMessage().toString());
		this.errorConstant = categoryErrorConstant;
	}

	@Override
	public CategoryErrorConstants getErrorConstant() {
		return errorConstant;
	}
}
