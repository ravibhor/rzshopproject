package com.rz.product.exceptions;

import com.rz.core.shop.base.exceptions.APIException;
import com.rz.product.base.constants.SubCategoryErrorConstants;

public class SubCategoryApiException extends APIException{

	private static final long serialVersionUID = -5304621011601779424L;
	private final SubCategoryErrorConstants errorConstant;
	
	public SubCategoryApiException(SubCategoryErrorConstants subCategoryErrorConstant) {
		super(subCategoryErrorConstant.getMessage().toString());
		this.errorConstant = subCategoryErrorConstant;
	}
	@Override
	public SubCategoryErrorConstants getErrorConstant() {
		return errorConstant;
	}

}
