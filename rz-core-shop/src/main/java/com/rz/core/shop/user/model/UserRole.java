package com.rz.core.shop.user.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.rz.core.shop.common.model.BaseEntity;


@Entity
@Table(name = "user_role_tbl")
public class UserRole extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	// user
	@ManyToOne(fetch = FetchType.EAGER)
	private User user;
	// role
	@ManyToOne
	private Role role;

	public UserRole() {
		super();
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public UserRole(User user, Role role) {
		super();
		this.user = user;
		this.role = role;
	}

	@Override
	public String toString() {
		return "UserRole [user=" + user + ", role=" + role + "]";
	}

}
