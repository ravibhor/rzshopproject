package com.rz.product.dto;

import java.util.List;

import com.rz.core.shop.base.models.response.ResponseBody;

public class SubCategoryListResponseModel extends ResponseBody {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4064192700445993030L;

	List<SubCategoryDto> subCategoryList;

	public List<SubCategoryDto> getSubCategoryList() {
		return subCategoryList;
	}

	public void setSubCategoryList(List<SubCategoryDto> subCategoryList) {
		this.subCategoryList = subCategoryList;
	}

}
