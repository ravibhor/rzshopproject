package com.rz.product.dto;

import java.io.Serializable;
import java.util.List;

import com.rz.core.shop.base.models.response.ResponseBody;

public class ImageListResponseModel extends ResponseBody implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5223049150158061124L;
	private ProductDto product;
	List<ProductImageDto> imageList;

	public List<ProductImageDto> getImageList() {
		return imageList;
	}

	public void setImageList(List<ProductImageDto> imageList) {
		this.imageList = imageList;
	}

	public ProductDto getProduct() {
		return product;
	}

	public void setProduct(ProductDto product) {
		this.product = product;
	}

}
