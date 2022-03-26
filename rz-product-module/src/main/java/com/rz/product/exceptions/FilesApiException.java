package com.rz.product.exceptions;

import com.rz.core.shop.base.constants.ErrorConstants;
import com.rz.core.shop.base.exceptions.APIException;
import com.rz.product.base.constants.FilesErrorConstants;

public class FilesApiException extends APIException {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6614620256799388153L;
	private final FilesErrorConstants errorConstant;

	public FilesApiException(FilesErrorConstants filesErrorConstants) {
		super(filesErrorConstants.getMessage().toString());
		this.errorConstant =filesErrorConstants;
	}

	@Override
	public ErrorConstants getErrorConstant() {
		return errorConstant;
	}

}
