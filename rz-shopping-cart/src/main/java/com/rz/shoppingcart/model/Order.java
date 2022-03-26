package com.rz.shoppingcart.model;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.validation.Valid;

import com.rz.core.shop.common.model.AbstractEntity;
import com.rz.core.shop.common.model.Billing;
import com.rz.core.shop.common.model.Delivery;
import com.rz.core.shop.user.model.User;

@Entity
@Table(name = "ORDERS")
public class Order extends AbstractEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@TableGenerator(name = "ORDER_GEN", table = "SM_SEQUENCER", pkColumnName = "SEQ_NAME", valueColumnName = "SEQ_COUNT", pkColumnValue = "ORDER_SEQ_NEXT_VAL", initialValue = 701, allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "ORDER_GEN")
	@Column(name = "ORDER_ID")
	private Long id;

	@Column(name = "ORDER_STATUS")
	@Enumerated(value = EnumType.STRING)
	private OrderStatus orderStatus=OrderStatus.ORDERED;

	@Column(name = "total_price")
    private Double totalPrice;

	@OneToMany(mappedBy = "order", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<OrderItem> orderItems;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

	@Column(name = "ORDER_TOTAL")
	private BigDecimal total;

	@Column(name = "CART_CODE", nullable = true)
	private String shoppingCartCode;

	@Column(name = "ORDER_TYPE")
	@Enumerated(value = EnumType.STRING)
	private OrderType orderType = OrderType.ORDER;

	@Column(name = "PAYMENT_TYPE")
	@Enumerated(value = EnumType.STRING)
	private PaymentType paymentType;

	@Column(name = "CONFIRMED_ADDRESS")
	private Boolean confirmedAddress = false;

	@Embedded
	private Delivery delivery = null;

	@Embedded
	private Billing billing = null;

	@Column(name = "CUSTOMER_EMAIL_ADDRESS", length = 50)
	private String customerEmailAddress;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public OrderStatus getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(OrderStatus orderStatus) {
		this.orderStatus = orderStatus;
	}

	public Double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(Double totalPrice) {
		this.totalPrice = totalPrice;
	}

	public List<OrderItem> getOrderItems() {
		return orderItems;
	}

	public void setOrderItems(List<OrderItem> orderItems) {
		this.orderItems = orderItems;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public BigDecimal getTotal() {
		return total;
	}

	public void setTotal(BigDecimal total) {
		this.total = total;
	}

	public String getShoppingCartCode() {
		return shoppingCartCode;
	}

	public void setShoppingCartCode(String shoppingCartCode) {
		this.shoppingCartCode = shoppingCartCode;
	}

	public OrderType getOrderType() {
		return orderType;
	}

	public void setOrderType(OrderType orderType) {
		this.orderType = orderType;
	}

	public PaymentType getPaymentType() {
		return paymentType;
	}

	public void setPaymentType(PaymentType paymentType) {
		this.paymentType = paymentType;
	}

	public Boolean getConfirmedAddress() {
		return confirmedAddress;
	}

	public void setConfirmedAddress(Boolean confirmedAddress) {
		this.confirmedAddress = confirmedAddress;
	}

	public Delivery getDelivery() {
		return delivery;
	}

	public void setDelivery(Delivery delivery) {
		this.delivery = delivery;
	}

	/*
	 * public Billing getBilling() { return billing; }
	 * 
	 * public void setBilling(Billing billing) { this.billing = billing; }
	 */

	public String getCustomerEmailAddress() {
		return customerEmailAddress;
	}

	public void setCustomerEmailAddress(String customerEmailAddress) {
		this.customerEmailAddress = customerEmailAddress;
	}

	public Order() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Order(Long id, OrderStatus orderStatus,Double totalPrice, List<OrderItem> orderItems,
			User user, BigDecimal total, String shoppingCartCode, OrderType orderType, PaymentType paymentType,
			Boolean confirmedAddress, Delivery delivery,String customerEmailAddress) {
		super();
		this.id = id;
		this.orderStatus = orderStatus;
		this.totalPrice = totalPrice;
		this.orderItems = orderItems;
		this.user = user;
		this.total = total;
		this.shoppingCartCode = shoppingCartCode;
		this.orderType = orderType;
		this.paymentType = paymentType;
		this.confirmedAddress = confirmedAddress;
		this.delivery = delivery;
		this.customerEmailAddress = customerEmailAddress;
	}

	@Override
	public String toString() {
		return "Order [id=" + id + ", orderStatus=" + orderStatus + ",totalPrice=" + totalPrice
				+ ", orderItems=" + orderItems + ", user=" + user + ", total=" + total + ", shoppingCartCode="
				+ shoppingCartCode + ", orderType=" + orderType + ", paymentType=" + paymentType + ", confirmedAddress="
				+ confirmedAddress + ", delivery=" + delivery + ", customerEmailAddress="
				+ customerEmailAddress + "]";
	}

	

}