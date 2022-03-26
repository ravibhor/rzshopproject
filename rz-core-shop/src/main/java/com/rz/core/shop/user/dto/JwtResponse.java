package com.rz.core.shop.user.dto;

import java.io.Serializable;

import com.rz.core.shop.base.models.response.ResponseBody;

public class JwtResponse extends ResponseBody implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private long id;
	public String username;
	private String email;
	private String accessToken;

	public JwtResponse() {
		super();
	}

	public JwtResponse(long id, String username, String email, String accessToken) {
		super();
		this.id = id;
		this.username = username;
		this.email = email;
		this.accessToken = accessToken;
	}

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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

}
