package com.rz.shoppingcart.dto;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.rz.core.shop.base.enums.StatusEnum;
import com.rz.core.shop.base.models.response.ResponseBody;
import com.rz.core.shop.common.model.Billing;
import com.rz.core.shop.common.model.Delivery;
import com.rz.core.shop.user.dto.UserDto;
import com.rz.core.shop.user.model.User;
import com.rz.shoppingcart.model.OrderItem;
import com.rz.shoppingcart.model.OrderStatus;
import com.rz.shoppingcart.model.OrderType;
import com.rz.shoppingcart.model.PaymentType;

public class OrderDto extends ResponseBody {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long id;
	private long userId;
	private double totalPrice;
	@Enumerated(value = EnumType.STRING)
	private OrderStatus orderStatus;
	//private UserDto user;
	List<OrderItemDto> orderItems;
	private BigDecimal total;
	private String shoppingCartCode;
	@Enumerated(value = EnumType.STRING)
	private OrderType orderType = OrderType.ORDER;
	@Enumerated(value = EnumType.STRING)
	private PaymentType paymentType;
	private Boolean confirmedAddress = false;
	private Delivery delivery = null;
	private Billing billing = null;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.sss'Z'")
	private Date createdAt;
	private String createdBy;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.sss'Z'")
	private Date updatedAt;
	private String updatedBy;
	@Enumerated(EnumType.STRING)
	private StatusEnum status;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}

	public OrderStatus getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(OrderStatus orderStatus) {
		this.orderStatus = orderStatus;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public Date getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}

	public String getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

	public StatusEnum getStatus() {
		return status;
	}

	public void setStatus(StatusEnum status) {
		this.status = status;
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

	public Billing getBilling() {
		return billing;
	}

	public void setBilling(Billing billing) {
		this.billing = billing;
	}

	public List<OrderItemDto> getOrderItems() {
		return orderItems;
	}

	public void setOrderItems(List<OrderItemDto> orderItems) {
		this.orderItems = orderItems;
	}

}