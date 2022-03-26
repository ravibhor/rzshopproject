package com.rz.product.dto;

import java.io.Serializable;
import java.util.List;

import com.rz.core.shop.base.models.response.ResponseBody;

public class ProductListResponseModel extends ResponseBody implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7815259659368212270L;
	List<ProductDto> productList;

	public List<ProductDto> getProductList() {
		return productList;
	}

	public void setProductList(List<ProductDto> productList) {
		this.productList = productList;
	}

}
