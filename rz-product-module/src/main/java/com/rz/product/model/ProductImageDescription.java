package com.rz.product.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

import com.rz.core.shop.common.model.AbstractEntity;

@Entity
@Table(name = "PRODUCT_IMAGE_DESCRIPTION")
/*
 * uniqueConstraints =
 * 
 * @UniqueConstraint(columnNames = { "PRODUCT_IMAGE_ID" }) })
 */
public class ProductImageDescription extends AbstractEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6363676069479945707L;

	@Id
	@Column(name = "DESCRIPTION_ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	// @NotEmpty
	@Column(name = "NAME", nullable = false, length = 120)
	private String name;

	@Column(name = "TITLE", length = 100)
	private String title;

	@Column(name = "DESCRIPTION")
	@Type(type = "org.hibernate.type.TextType")
	private String description;

	/*
	 * @ManyToOne(targetEntity = ProductImage.class)
	 * 
	 * @JoinColumn(name = "PRODUCT_IMAGE_ID", nullable = false) private ProductImage
	 * productImage;
	 */
	@Column(name = "ALT_TAG", length = 100)
	private String altTag;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getAltTag() {
		return altTag;
	}

	public void setAltTag(String altTag) {
		this.altTag = altTag;
	}

	public ProductImageDescription() {
		super();
	}

	public ProductImageDescription(Long id, String name, String title, String description, String altTag) {
		super();
		this.id = id;
		this.name = name;
		this.title = title;
		this.description = description;
		this.altTag = altTag;
	}

	@Override
	public String toString() {
		return "ProductImageDescription [id=" + id + ", name=" + name + ", title=" + title + ", description="
				+ description + ", altTag=" + altTag + "]";
	}

}
