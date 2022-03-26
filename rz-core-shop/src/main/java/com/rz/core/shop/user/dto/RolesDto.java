package com.rz.core.shop.user.dto;

import com.rz.core.shop.base.models.response.ResponseBody;

public class RolesDto extends ResponseBody {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id;
	private String  roleName;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	
}
