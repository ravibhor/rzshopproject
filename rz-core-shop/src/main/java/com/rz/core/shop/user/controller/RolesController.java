package com.rz.core.shop.user.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rz.core.shop.base.BaseController;
import com.rz.core.shop.base.constants.HttpStatusCodes;
import com.rz.core.shop.base.enums.UserMessages;
import com.rz.core.shop.base.models.response.Response;
import com.rz.core.shop.user.dto.RolesDto;
import com.rz.core.shop.user.dto.UserDto;
import com.rz.core.shop.user.services.RolesService;
import com.rz.core.shop.user.services.UserService;

/**
 * @author ravi
 *
 */
@CrossOrigin(origins = "*")
@RestController
@RequestMapping(value = "/api/v1")
@SuppressWarnings({ "unused" })
public class RolesController extends BaseController{
	private static final Logger LOGGER = LoggerFactory.getLogger(UserApiController.class);

	@Autowired
	private RolesService rolesService;

	/**
	 * @param rolesDto
	 * @return
	 * @throws Exception
	 */
	@PostMapping(value = "/users/roles")
	public ResponseEntity<Response> createUser(@RequestBody RolesDto rolesDto) throws Exception {
		RolesDto dto = rolesService.createRoles(rolesDto);
		LOGGER.info("Roles created...");
		return createResponse(HttpStatusCodes.SUCCESS_CREATED, UserMessages.ROLE_CREATED, null, 0, dto);
	}


}
