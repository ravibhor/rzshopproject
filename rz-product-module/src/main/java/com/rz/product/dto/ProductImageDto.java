package com.rz.product.dto;

import java.util.Date;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.rz.core.shop.base.enums.StatusEnum;
import com.rz.core.shop.base.models.response.ResponseBody;

public class ProductImageDto extends ResponseBody {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6925813416664372345L;
	private Long id;
	private String productImgName;
	private String productImgUrl;
	private Long productImgSize;
	private String productImgType;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.sss'Z'")
	private Date createdAt;
	private String createdBy;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.sss'Z'")
	private Date updatedAt;
	private String updatedBy;
	@Enumerated(EnumType.STRING)
	private StatusEnum status;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getProductImgName() {
		return productImgName;
	}

	public void setProductImgName(String productImgName) {
		this.productImgName = productImgName;
	}

	public String getProductImgUrl() {
		return productImgUrl;
	}

	public void setProductImgUrl(String productImgUrl) {
		this.productImgUrl = productImgUrl;
	}

	public Long getProductImgSize() {
		return productImgSize;
	}

	public void setProductImgSize(Long productImgSize) {
		this.productImgSize = productImgSize;
	}

	public String getProductImgType() {
		return productImgType;
	}

	public void setProductImgType(String productImgType) {
		this.productImgType = productImgType;
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
