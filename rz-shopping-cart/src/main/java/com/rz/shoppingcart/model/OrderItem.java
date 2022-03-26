package com.rz.shoppingcart.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.rz.core.shop.common.model.AbstractEntity;
import com.rz.product.model.Product;

@Entity
@Table(name = "ORDER_ITEM")
public class OrderItem extends AbstractEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7529420695100931674L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long orderItemId;

	@Column(name = "quantity")
	private long quantity;

	@Column(name = "productPrice")
	private double productPrice;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ORDER_ID")
	@JsonIgnore
	private Order order;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "productId")
	private Product product;

	public long getOrderItemId() {
		return orderItemId;
	}

	public void setOrderItemId(long orderItemId) {
		this.orderItemId = orderItemId;
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

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public OrderItem() {
		super();
	}

	public OrderItem(long orderItemId, long quantity, double productPrice, Order order, Product product) {
		super();
		this.orderItemId = orderItemId;
		this.quantity = quantity;
		this.productPrice = productPrice;
		this.order = order;
		this.product = product;
	}
	

	public OrderItem(long quantity, double productPrice, Order orderId, Product productId) {
		super();
		this.quantity = quantity;
		this.productPrice = productPrice;
		this.order = orderId;
		this.product = productId;
	}

	@Override
	public String toString() {
		return "OrderItem [orderItemId=" + orderItemId + ", quantity=" + quantity + ", productPrice=" + productPrice
				+ ", order=" + order + ", product=" + product + "]";
	}

}
