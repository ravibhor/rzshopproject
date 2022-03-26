package com.rz.shoppingcart.model;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

import com.rz.core.shop.common.model.AbstractEntity;
import com.rz.product.model.Product;

@Entity
@Table(name = "SHOPPING_CART_ITEM")
public class ShoppingCartItem extends AbstractEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@TableGenerator(name = "SHOPPING_CART_ITEM_GEN", table = "SM_SEQUENCER", pkColumnName = "SEQ_NAME", valueColumnName = "SEQ_COUNT", pkColumnValue = "SHOPPING_CART_ITEM_SEQ_NEXT_VAL", initialValue = 601, allocationSize = 2)
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "SHOPPING_CART_ITEM_GEN")
	private Long id;

	/*
	 * @JsonIgnore
	 * 
	 * @ManyToOne(fetch = FetchType.LAZY)
	 * 
	 * @JoinColumn(name = "SHP_CART_ID") private ShoppingCart shoppingCart;
	 */

	@Column(name = "QUANTITY")
	private long quantity;

	@Column(name = "PRODUCTPRICE")
	private double productPrice;

	@Column(name = "PRODUCTCODE")
	private int productCode;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "product_id")
	private Product product;

	@Column(name = "USER_ID", nullable = true)
	private long userId;

	@Column(name = "SUB_TOTAL", nullable = true)
	private BigDecimal total;

	public Long getId() {
		return id;
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

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public ShoppingCartItem() {
		super();
	}

	public BigDecimal getTotal() {
		return total;
	}

	public void setTotal(BigDecimal total) {
		this.total = total;
	}

	public BigDecimal calculateTotal() {
		double total = this.quantity * this.productPrice;
		return BigDecimal.valueOf(total);
	}

	public ShoppingCartItem(Long id, long quantity, double productPrice, int productCode, Product product, long userId,
			BigDecimal total) {
		super();
		this.id = id;
		this.quantity = quantity;
		this.productPrice = productPrice;
		this.productCode = productCode;
		this.product = product;
		this.userId = userId;
		this.total = total;
	}

	@Override
	public String toString() {
		return "ShoppingCartItem [id=" + id + ", quantity=" + quantity + ", productPrice=" + productPrice
				+ ", productCode=" + productCode + ", product=" + product + ", userId=" + userId + ", total=" + total
				+ "]";
	}

}
