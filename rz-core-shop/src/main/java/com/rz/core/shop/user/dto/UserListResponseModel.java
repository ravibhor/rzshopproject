package com.rz.core.shop.user.dto;

import java.io.Serializable;
import java.util.List;

import com.rz.core.shop.base.models.response.ResponseBody;

public class UserListResponseModel extends ResponseBody implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6688270571757380308L;

	List<UserDto> userList;

	public List<UserDto> getUserList() {
		return userList;
	}

	public void setUserList(List<UserDto> userList) {
		this.userList = userList;
	}

}
