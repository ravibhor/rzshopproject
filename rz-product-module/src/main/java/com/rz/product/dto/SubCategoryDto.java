package com.rz.product.dto;

import java.util.Date;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.rz.core.shop.base.enums.StatusEnum;
import com.rz.core.shop.base.models.response.ResponseBody;

public class SubCategoryDto extends ResponseBody {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7128968051384245632L;

	private Long subCategoryId;

	private String subCategoryTitle;

	private String subCategoryDescription;

	private String subCategoryImgName;

	private String subCategoryImgUrl;

	private CategoryDto categoryDto;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.sss'Z'")
	private Date createdAt;
	private String createdBy;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.sss'Z'")
	private Date updatedAt;
	private String updatedBy;
	@Enumerated(EnumType.STRING)
	private StatusEnum status;

	public Long getSubCategoryId() {
		return subCategoryId;
	}

	public void setSubCategoryId(Long subCategoryId) {
		this.subCategoryId = subCategoryId;
	}

	public String getSubCategoryTitle() {
		return subCategoryTitle;
	}

	public void setSubCategoryTitle(String subCategoryTitle) {
		this.subCategoryTitle = subCategoryTitle;
	}

	public String getSubCategoryDescription() {
		return subCategoryDescription;
	}

	public void setSubCategoryDescription(String subCategoryDescription) {
		this.subCategoryDescription = subCategoryDescription;
	}

	public String getSubCategoryImgName() {
		return subCategoryImgName;
	}

	public void setSubCategoryImgName(String subCategoryImgName) {
		this.subCategoryImgName = subCategoryImgName;
	}

	public String getSubCategoryImgUrl() {
		return subCategoryImgUrl;
	}

	public void setSubCategoryImgUrl(String subCategoryImgUrl) {
		this.subCategoryImgUrl = subCategoryImgUrl;
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

	public CategoryDto getCategoryDto() {
		return categoryDto;
	}

	public void setCategoryDto(CategoryDto categoryDto) {
		this.categoryDto = categoryDto;
	}

}
