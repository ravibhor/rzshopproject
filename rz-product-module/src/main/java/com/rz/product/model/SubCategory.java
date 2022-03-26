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
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.rz.core.shop.common.model.AbstractEntity;

@Entity
@Table(name = "SUBCATEGORY")
public class SubCategory extends AbstractEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 831463700267265667L;

	@TableGenerator(name = "SUBCATEGORY_GEN", table = "SM_SEQUENCER", pkColumnName = "SEQ_NAME", valueColumnName = "SEQ_COUNT", pkColumnValue = "SUBCATEGORY_SEQ_NEXT_VAL", initialValue = 401, allocationSize = 1)
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "SUBCATEGORY_GEN")
	@Column(name = "SUBCATEGORY_ID")
	private Long subCategoryId;

	@Column(name = "SUBCATEGORY_TITLE")
	private String subCategoryTitle;

	@Column(name = "SUBCATEGORY_DESCRIPTION")
	private String subCategoryDescription;

	@Column(name = "SUBCATEGORY_IMG_NAME")
	private String subCategoryImgName;

	@Column(name = "SUBCATEGORY_IMG_URL")
	private String subCategoryImgUrl;

	@Column(name = "SUBCATEGORY_IMG_PIC_BYTE")
	@Lob
	private byte[] subCatgoryImgPicByte;

	@OneToMany(mappedBy = "subCategory", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JsonIgnore
	private Set<Product> product = new LinkedHashSet<>();

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "category_id", nullable = false)
	private Category category;

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

	public byte[] getSubCatgoryImgPicByte() {
		return subCatgoryImgPicByte;
	}

	public void setSubCatgoryImgPicByte(byte[] subCatgoryImgPicByte) {
		this.subCatgoryImgPicByte = subCatgoryImgPicByte;
	}

	public Set<Product> getProduct() {
		return product;
	}

	public void setProduct(Set<Product> product) {
		this.product = product;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public SubCategory() {
		super();
	}

	public SubCategory(Long subCategoryId, String subCategoryTitle, String subCategoryDescription,
			String subCategoryImgName, String subCategoryImgUrl, byte[] subCatgoryImgPicByte, Set<Product> product,
			Category category) {
		super();
		this.subCategoryId = subCategoryId;
		this.subCategoryTitle = subCategoryTitle;
		this.subCategoryDescription = subCategoryDescription;
		this.subCategoryImgName = subCategoryImgName;
		this.subCategoryImgUrl = subCategoryImgUrl;
		this.subCatgoryImgPicByte = subCatgoryImgPicByte;
		this.product = product;
		this.category = category;
	}

	@Override
	public String toString() {
		return "SubCategory [subCategoryId=" + subCategoryId + ", subCategoryTitle=" + subCategoryTitle
				+ ", subCategoryDescription=" + subCategoryDescription + ", subCategoryImgName=" + subCategoryImgName
				+ ", subCategoryImgUrl=" + subCategoryImgUrl + ", subCatgoryImgPicByte="
				+ Arrays.toString(subCatgoryImgPicByte) + ", product=" + product + ", category=" + category + "]";
	}

}
