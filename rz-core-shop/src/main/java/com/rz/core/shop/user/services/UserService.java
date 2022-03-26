package com.rz.core.shop.user.services;

import java.util.List;

import org.apache.tomcat.websocket.AuthenticationException;

import com.rz.core.shop.user.dto.UserDto;

public interface UserService {

	String authenticatedUser() throws AuthenticationException;

	UserDto createUser(UserDto dto) throws Exception;

	public boolean checkIfUserExists(final String username) throws Exception;

	public boolean checkIfEmailExists(final String email) throws Exception;

	UserDto getUserById(long id) throws Exception;
	
	List<UserDto> getUsers() throws Exception;

	public void deleteUserById(long id) throws Exception;

	UserDto updateUser(UserDto dto, long id) throws Exception;

}
