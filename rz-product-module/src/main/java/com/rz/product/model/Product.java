package com.rz.product.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.persistence.UniqueConstraint;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.rz.core.shop.common.model.AbstractEntity;
import com.rz.shoppingcart.model.ShoppingCartItem;

@Entity
@Table(name = "PRODUCT", uniqueConstraints = { @UniqueConstraint(columnNames = "PRODUCT_ID") })
public class Product extends AbstractEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@TableGenerator(name = "PRODUCT_GEN", table = "SM_SEQUENCER", pkColumnName = "SEQ_NAME", valueColumnName = "SEQ_COUNT", pkColumnValue = "PRODUCT_SEQ_NEXT_VAL", initialValue = 201, allocationSize = 1)
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "PRODUCT_GEN")
	@Column(name = "PRODUCT_ID")
	private Long productId;

	@Column(name = "PRODUCT_TITLE")
	private String productTitle;

	@Column(name = "PRODUCT_PRICE")
	private double productPrice;

	@Column(name = "PRODUCT_CODE")
	private long productCode;

	@Column(name = "PRODUCT_SIZE")
	private String productSize;

	@Column(name = "TOTAL_PRODUCT")
	private String totalProduct;

	@Column(name = "PRODUCT_DESCRIPTION")
	private String productDescription;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "subcategory_id", nullable = false)
	private SubCategory subCategory;

	@OneToMany(mappedBy = "product", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<ProductImage> images = new ArrayList<ProductImage>();
	

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

	public List<ProductImage> getImages() {
		return images;
	}

	public void setImages(List<ProductImage> images) {
		this.images = images;
	}

	public SubCategory getSubCategory() {
		return subCategory;
	}

	public void setSubCategory(SubCategory subCategory) {
		this.subCategory = subCategory;
	}

	public Product() {
		super();
	}

	public Product(Long productId, String productTitle, double productPrice, long productCode, String productSize,
			String totalProduct, String productDescription, SubCategory subCategory, List<ProductImage> images) {
		super();
		this.productId = productId;
		this.productTitle = productTitle;
		this.productPrice = productPrice;
		this.productCode = productCode;
		this.productSize = productSize;
		this.totalProduct = totalProduct;
		this.productDescription = productDescription;
		this.subCategory = subCategory;
		this.images = images;
	}

	@Override
	public String toString() {
		return "Product [productId=" + productId + ", productTitle=" + productTitle + ", productPrice=" + productPrice
				+ ", productCode=" + productCode + ", productSize=" + productSize + ", totalProduct=" + totalProduct
				+ ", productDescription=" + productDescription + ", subCategory=" + subCategory + ", images=" + images
				+ "]";
	}

}
