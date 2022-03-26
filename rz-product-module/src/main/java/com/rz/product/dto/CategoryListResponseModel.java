package com.rz.product.dto;

import java.util.List;

import com.rz.core.shop.base.models.response.ResponseBody;

public class CategoryListResponseModel extends ResponseBody {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	List<CategoryDto> categoryList;

	public List<CategoryDto> getCategoryList() {
		return categoryList;
	}

	public void setCategoryList(List<CategoryDto> categoryList) {
		this.categoryList = categoryList;
	}

}
