package com.rz.product.dto;

import java.util.Date;
import java.util.List;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.rz.core.shop.base.enums.StatusEnum;
import com.rz.core.shop.base.models.response.ResponseBody;

public class ProductDto extends ResponseBody {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3975230850514961265L;

	private Long productId;
	private String productTitle;
	private double productPrice;
	private long productCode;
	private String productSize;
	private String totalProduct;
	private String productDescription;
	private SubCategoryDto subCategoryDto;
	private List<ProductImageDto> images;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.sss'Z'")
	private Date createdAt;
	private String createdBy;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.sss'Z'")
	private Date updatedAt;
	private String updatedBy;
	@Enumerated(EnumType.STRING)
	private StatusEnum status;

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public String getProductTitle() {
		return productTitle;
	}

	public void setProductTitle(String productTitle) {
		this.productTitle = productTitle;
	}

	public double getProductPrice() {
		return productPrice;
	}

	public void setProductPrice(double productPrice) {
		this.productPrice = productPrice;
	}

	public long getProductCode() {
		return productCode;
	}

	public void setProductCode(long productCode) {
		this.productCode = productCode;
	}

	public String getProductSize() {
		return productSize;
	}

	public void setProductSize(String productSize) {
		this.productSize = productSize;
	}

	public String getTotalProduct() {
		return totalProduct;
	}

	public void setTotalProduct(String totalProduct) {
		this.totalProduct = totalProduct;
	}

	public String getProductDescription() {
		return productDescription;
	}

	public void setProductDescription(String productDescription) {
		this.productDescription = productDescription;
	}

	public SubCategoryDto getSubCategoryDto() {
		return subCategoryDto;
	}

	public void setSubCategoryDto(SubCategoryDto subCategoryDto) {
		this.subCategoryDto = subCategoryDto;
	}

	public List<ProductImageDto> getImages() {
		return images;
	}

	public void setImages(List<ProductImageDto> images) {
		this.images = images;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public Date getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}

	public String getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

	public StatusEnum getStatus() {
		return status;
	}

	public void setStatus(StatusEnum status) {
		this.status = status;
	}

}
