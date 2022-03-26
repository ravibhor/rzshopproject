package com.rz.core.shop.user.controller;

import java.util.List;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rz.core.shop.base.BaseController;
import com.rz.core.shop.base.constants.Constants;
import com.rz.core.shop.base.constants.HttpStatusCodes;
import com.rz.core.shop.base.enums.ERole;
import com.rz.core.shop.base.enums.UserMessages;
import com.rz.core.shop.base.models.response.Response;
import com.rz.core.shop.user.dto.UserDto;
import com.rz.core.shop.user.dto.UserListResponseModel;
import com.rz.core.shop.user.model.Role;
import com.rz.core.shop.user.services.UserService;

/**
 * @author ravi
 *
 */
@CrossOrigin(origins = "*")
@RestController
@RequestMapping(value = "/api/v1")
@SuppressWarnings({ "unused" })
public class UserApiController extends BaseController {

	private static final Logger LOGGER = LoggerFactory.getLogger(UserApiController.class);

	@Autowired
	private UserService userService;

	/**
	 * Create a new customer
	 * 
	 * @param userDto
	 * @return
	 * @throws Exception
	 */
	@PostMapping(value = "/users/register", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Response> createUser(@Valid @RequestBody UserDto userDto) throws Exception {
		boolean username = userService.checkIfUserExists(userDto.getUsername());
		boolean email = userService.checkIfEmailExists(userDto.getEmail());
		UserDto dto = userService.createUser(userDto);
		LOGGER.info("User created...");
		return createResponse(HttpStatusCodes.SUCCESS_CREATED, UserMessages.USER_CREATED, null, 0, dto);
	}

	/**
	 * get user by id
	 * 
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@GetMapping(value = "/users/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Response> getUserById(@PathVariable long id) throws Exception {
		UserDto dto = userService.getUserById(id);
		LOGGER.info("Get user data...");
		return createResponse(HttpStatusCodes.SUCCESS_OK, 0, dto);
	}

	/**
	 * delete user by id
	 * 
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@DeleteMapping(value = "/users/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Response> deleteById(@PathVariable long id) throws Exception {
		userService.deleteUserById(id);
		LOGGER.info("User deleted...");
		return createResponse(HttpStatusCodes.SUCCESS_OK, 0);
	}

	/**
	 * update a user
	 * 
	 * @param userDto
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@PutMapping(value = "/users/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Response> updateUser(@RequestBody UserDto userDto, @PathVariable long id) throws Exception {
		UserDto dto = userService.updateUser(userDto, id);
		LOGGER.info("User updated...");
		return createResponse(HttpStatusCodes.SUCCESS_OK, 0, dto);
	}
	
	@GetMapping(value = "/users", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Response> getUsers() throws Exception {
		List<UserDto> list = userService.getUsers();
		UserListResponseModel response=new UserListResponseModel();
		response.setUserList(list);
		LOGGER.info("Get user data...");
		return createResponse(HttpStatusCodes.SUCCESS_OK, 0, response);
	}

}
