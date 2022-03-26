package com.rz.product.model;

import java.io.Serializable;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.rz.core.shop.common.model.AbstractEntity;

@Entity
@Table(name = "CATEGORY")
public class Category extends AbstractEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5624330197942184938L;

	@TableGenerator(name = "CATEGORY_GEN", table = "SM_SEQUENCER", pkColumnName = "SEQ_NAME", valueColumnName = "SEQ_COUNT", pkColumnValue = "CATEGORY_SEQ_NEXT_VAL", initialValue = 101, allocationSize = 1)
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "CATEGORY_GEN")
	@Column(name = "CATEGORY_ID")
	private Long categoryId;

	@Column(name = "CATEGORY_TITLE")
	private String categoryTitle;

	@Column(name = "CATEGORY_DESCRIPTION")
	private String categoryDescription;

	@Column(name = "CATEGORY_IMG_NAME")
	private String categoryImgName;

	@Column(name = "CATEGORY_IMG_URL")
	private String categoryImgUrl;

	@Column(name = "CATEGORY_IMG_PIC_BYTE")
	@Lob
	private byte[] catgoryImgPicByte;

	@OneToMany(mappedBy = "category", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JsonIgnore
	private Set<SubCategory> subCategory = new LinkedHashSet<>();

	public Long getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}

	public String getCategoryTitle() {
		return categoryTitle;
	}

	public void setCategoryTitle(String categoryTitle) {
		this.categoryTitle = categoryTitle;
	}

	public String getCategoryDescription() {
		return categoryDescription;
	}

	public void setCategoryDescription(String categoryDescription) {
		this.categoryDescription = categoryDescription;
	}

	public String getCategoryImgName() {
		return categoryImgName;
	}

	public void setCategoryImgName(String categoryImgName) {
		this.categoryImgName = categoryImgName;
	}

	public String getCategoryImgUrl() {
		return categoryImgUrl;
	}

	public void setCategoryImgUrl(String categoryImgUrl) {
		this.categoryImgUrl = categoryImgUrl;
	}

	public byte[] getCatgoryImgPicByte() {
		return catgoryImgPicByte;
	}

	public void setCatgoryImgPicByte(byte[] catgoryImgPicByte) {
		this.catgoryImgPicByte = catgoryImgPicByte;
	}

	public Set<SubCategory> getSubCategory() {
		return subCategory;
	}

	public void setSubCategory(Set<SubCategory> subCategory) {
		this.subCategory = subCategory;
	}

	public Category() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Category(Long categoryId, String categoryTitle, String categoryDescription, String categoryImgName,
			String categoryImgUrl, byte[] catgoryImgPicByte, Set<SubCategory> subCategory) {
		super();
		this.categoryId = categoryId;
		this.categoryTitle = categoryTitle;
		this.categoryDescription = categoryDescription;
		this.categoryImgName = categoryImgName;
		this.categoryImgUrl = categoryImgUrl;
		this.catgoryImgPicByte = catgoryImgPicByte;
		this.subCategory = subCategory;
	}

	@Override
	public String toString() {
		return "Category [categoryId=" + categoryId + ", categoryTitle=" + categoryTitle + ", categoryDescription="
				+ categoryDescription + ", categoryImgName=" + categoryImgName + ", categoryImgUrl=" + categoryImgUrl
				+ ", catgoryImgPicByte=" + Arrays.toString(catgoryImgPicByte) + ", subCategory=" + subCategory + "]";
	}

	/*
	 * @OneToMany(mappedBy = "category", fetch = FetchType.EAGER, cascade =
	 * CascadeType.ALL)
	 * 
	 * @JsonIgnore private Set<Product> product = new LinkedHashSet<>();
	 */

}
