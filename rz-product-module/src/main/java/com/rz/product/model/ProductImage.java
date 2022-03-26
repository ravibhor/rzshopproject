package com.rz.product.model;

import java.io.Serializable;
import java.util.Arrays;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.rz.core.shop.common.model.AbstractEntity;

@Entity
@Table(name = "PRODUCT_IMAGE")
public class ProductImage extends AbstractEntity implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 5099570199083293224L;

	@Id
	@Column(name = "PRODUCT_IMAGE_ID")
	@TableGenerator(name = "PRODUCT_IMG_GEN", table = "SM_SEQUENCER", pkColumnName = "SEQ_NAME", valueColumnName = "SEQ_COUNT", pkColumnValue = "PRODUCT_IMG_SEQ_NEXT_VAL", initialValue = 301, allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "PRODUCT_IMG_GEN")
	private Long id;

	@Column(name = "PRODUCT_IMG_NAME")
	private String productImgName;

	@Column(name = "PRODUCT_IMG_URL")
	private String productImgUrl;

	@Column(name = "PRODUCT_IMG_SIZE")
	private Long productImgSize;

	@Column(name = "PRODUCT_IMG_TYPE")
	private String productImgType;

	@Column(name = "PRODUCT_IMG_PIC_BYTE")
	@Lob
	private byte[] productImgPicByte;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "PRODUCT_ID")
	@JsonIgnore
	private Product product;

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

	public byte[] getProductImgPicByte() {
		return productImgPicByte;
	}

	public void setProductImgPicByte(byte[] productImgPicByte) {
		this.productImgPicByte = productImgPicByte;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public ProductImage() {
		super();
	}

	public ProductImage(Long id, String productImgName, String productImgUrl, Long productImgSize,
			String productImgType, byte[] productImgPicByte, Product product) {
		super();
		this.id = id;
		this.productImgName = productImgName;
		this.productImgUrl = productImgUrl;
		this.productImgSize = productImgSize;
		this.productImgType = productImgType;
		this.productImgPicByte = productImgPicByte;
		this.product = product;
	}

	@Override
	public String toString() {
		return "ProductImage [id=" + id + ", productImgName=" + productImgName + ", productImgUrl=" + productImgUrl
				+ ", productImgSize=" + productImgSize + ", productImgType=" + productImgType + ", productImgPicByte="
				+ Arrays.toString(productImgPicByte) + ", product=" + product + "]";
	}

}
