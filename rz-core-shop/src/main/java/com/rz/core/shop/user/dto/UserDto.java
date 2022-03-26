package com.rz.core.shop.user.dto;

import java.util.Set;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import com.rz.core.shop.base.models.response.ResponseBody;

public class UserDto extends ResponseBody {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private long id;
	// @NotEmpty(message = "{NotEmpty.user.username}")
	// @Size(max = 10)
	private String username;

	@JsonProperty(access = Access.WRITE_ONLY)
	@NotEmpty(message = "{NotEmpty.password.required}")
	@Size(max = 20)
	private String password;

	@NotEmpty(message = "{Email.user.email}")
	@Size(max = 20)
	private String email;

	@NotEmpty(message = "{NotEmpty.user.firstName}")
	@Size(max = 20)
	private String firstName;

	@NotEmpty(message = "{NotEmpty.user.lastName}")
	@Size(max = 20)
	private String lastName;

	// @NotEmpty(message = "{NotEmpty.user.mobile}")
	// @Size(max = 20)
	private String mobile;

	private Set<String> role;

	private RolesDto roles;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public Set<String> getRole() {
		return role;
	}

	public void setRole(Set<String> role) {
		this.role = role;
	}

	public RolesDto getRoles() {
		return roles;
	}

	public void setRoles(RolesDto roles) {
		this.roles = roles;
	}

}
