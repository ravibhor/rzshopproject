/**
 * 
 */
package com.rz.shoppingcart.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

/**
 * <p>
 * Shopping cart is responsible for storing and carrying shopping cart
 * information.Shopping Cart consists of {@link ShoppingCartItem} which
 * represents individual lines items associated with the shopping cart
 * </p>
 *
 */
@Entity
@Table(name = "SHOPPING_CART")
public class ShoppingCart implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@TableGenerator(name = "SHOPPING_CART_GEN", table = "SM_SEQUENCER", pkColumnName = "SEQ_NAME", valueColumnName = "SEQ_COUNT", pkColumnValue = "SHOPPING_CART_SEQ_NEXT_VAL", initialValue = 501, allocationSize = 2)
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "SHOPPING_CART_GEN")
	private Long id;

	@Column(name = "SHP_CART_CODE", unique = true, nullable = false)
	private String shoppingCartCode;

	/*
	 * @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy =
	 * "shoppingCart") private Set<ShoppingCartItem> cartItem = new
	 * HashSet<ShoppingCartItem>();
	 */

	@Column(name = "CUSTOMER_ID", nullable = true)
	private Long customerId;

	@Column(name = "ORDER_ID", nullable = true)
	private Long orderId;

	@Column(name = "PROMO_CODE")
	private String promoCode;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "PROMO_ADDED")
	private Date promoAdded;

	@Transient
	private boolean obsolete = false;// when all items are obsolete

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getShoppingCartCode() {
		return shoppingCartCode;
	}

	public void setShoppingCartCode(String shoppingCartCode) {
		this.shoppingCartCode = shoppingCartCode;
	}

	public Long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}

	public Long getOrderId() {
		return orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

	public String getPromoCode() {
		return promoCode;
	}

	public void setPromoCode(String promoCode) {
		this.promoCode = promoCode;
	}

	public Date getPromoAdded() {
		return promoAdded;
	}

	public void setPromoAdded(Date promoAdded) {
		this.promoAdded = promoAdded;
	}

	public boolean isObsolete() {
		return obsolete;
	}

	public void setObsolete(boolean obsolete) {
		this.obsolete = obsolete;
	}

	public ShoppingCart() {
		super();
	}

	public ShoppingCart(Long id, String shoppingCartCode, Long customerId, Long orderId, String promoCode,
			Date promoAdded, boolean obsolete) {
		super();
		this.id = id;
		this.shoppingCartCode = shoppingCartCode;
		this.customerId = customerId;
		this.orderId = orderId;
		this.promoCode = promoCode;
		this.promoAdded = promoAdded;
		this.obsolete = obsolete;
	}

	@Override
	public String toString() {
		return "ShoppingCart [id=" + id + ", shoppingCartCode=" + shoppingCartCode + ", customerId=" + customerId
				+ ", orderId=" + orderId + ", promoCode=" + promoCode + ", promoAdded=" + promoAdded + ", obsolete="
				+ obsolete + "]";
	}

}
