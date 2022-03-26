package com.rz.core.shop.customer.model;

import java.util.Date;

import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

import com.rz.core.shop.base.enums.StatusEnum;
import com.rz.core.shop.common.model.BaseEntity;
import com.rz.core.shop.user.model.Role;

public class CustomerRole extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	// customer
	@ManyToOne(fetch = FetchType.EAGER)
	private Customer customer;

	// role
	@ManyToOne
	private Role role;

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public CustomerRole() {
		super();
	}

	public CustomerRole(long id, Date createdAt, String createdBy, Date updatedAt, String updatedBy,
			StatusEnum status) {
		super(id, createdAt, createdBy, updatedAt, updatedBy, status);
	}

	public CustomerRole(Customer customer, Role role) {
		super();
		this.customer = customer;
		this.role = role;
	}

	@Override
	public String toString() {
		return "CustomerRole [customer=" + customer + ", role=" + role + "]";
	}

}
