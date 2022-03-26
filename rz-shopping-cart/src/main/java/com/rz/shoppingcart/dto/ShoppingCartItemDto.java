package com.rz.shoppingcart.dto;

import java.io.Serializable;
import java.math.BigDecimal;

import com.rz.core.shop.base.models.response.ResponseBody;

public class ShoppingCartItemDto extends ResponseBody implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;
	private long quantity;
	private long productId;
	private long userId;
	private String name;
	private double productPrice;
	private int productCode;
	private BigDecimal total;
	private String promoCode;
	private String ShoppingCartCode;// shopping cart code

	public Long getId() {
		return id;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public long getQuantity() {
		return quantity;
	}

	public void setQuantity(long quantity) {
		this.quantity = quantity;
	}

	public long getProductId() {
		return productId;
	}

	public void setProductId(long productId) {
		this.productId = productId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getProductPrice() {
		return productPrice;
	}

	public void setProductPrice(double productPrice) {
		this.productPrice = productPrice;
	}

	public int getProductCode() {
		return productCode;
	}

	public void setProductCode(int productCode) {
		this.productCode = productCode;
	}

	public BigDecimal getTotal() {
		return total;
	}

	public void setTotal(BigDecimal total) {
		this.total = total;
	}

	public String getPromoCode() {
		return promoCode;
	}

	public void setPromoCode(String promoCode) {
		this.promoCode = promoCode;
	}

	public String getShoppingCartCode() {
		return ShoppingCartCode;
	}

	public void setShoppingCartCode(String shoppingCartCode) {
		ShoppingCartCode = shoppingCartCode;
	}

}
