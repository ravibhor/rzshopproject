package com.rz.core.shop.user.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.rz.core.shop.common.model.BaseEntity;
import com.rz.core.shop.customer.model.Customer;
import com.rz.core.shop.customer.model.CustomerRole;

@Entity
@Table(name = "role_tbl")
public class Role extends BaseEntity implements Serializable {

	/**
	* 
	*/
	private static final long serialVersionUID = -4846374745397902400L;

	@Column(name = "role_name")
	private String roleName;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "role")
	private Set<UserRole> userRole = new HashSet<UserRole>();

	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "customerRoles")
	private Set<Customer> customer = new HashSet<Customer>();

	public Role() {
		super();

	}

	public Role(String roleName, Set<UserRole> userRole, Set<Customer> customer) {
		super();
		this.roleName = roleName;
		this.userRole = userRole;
		this.customer = customer;
	}

	public Set<Customer> getCustomer() {
		return customer;
	}

	public void setCustomer(Set<Customer> customer) {
		this.customer = customer;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public Set<UserRole> getUserRole() {
		return userRole;
	}

	public void setUserRole(Set<UserRole> userRole) {
		this.userRole = userRole;
	}

	@Override
	public String toString() {
		return "Role [roleName=" + roleName + ", userRole=" + userRole + ", customer=" + customer + "]";
	}

}
