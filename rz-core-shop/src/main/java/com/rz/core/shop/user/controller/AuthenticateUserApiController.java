package com.rz.core.shop.user.controller;

import java.security.Principal;
import java.util.Set;
import java.util.stream.Collectors;

import javax.naming.AuthenticationException;

import org.hibernate.service.spi.InjectService;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rz.core.shop.base.BaseController;
import com.rz.core.shop.base.constants.HttpStatusCodes;
import com.rz.core.shop.base.enums.UserMessages;
import com.rz.core.shop.base.models.response.Response;
import com.rz.core.shop.security.common.config.JwtUtils;
import com.rz.core.shop.user.dto.AuthRequestDto;
import com.rz.core.shop.user.dto.JwtResponse;
import com.rz.core.shop.user.dto.UserDto;
import com.rz.core.shop.user.model.User;


/**
 * @author ravi
 *
 */
@CrossOrigin(origins = "*")
@RestController
@RequestMapping(value = "/api/v1")
public class AuthenticateUserApiController extends BaseController {

	private static final Logger LOGGER = LoggerFactory.getLogger(AuthenticateUserApiController.class);

	@Autowired
	private JwtUtils jwtUtil;

	@Autowired
	private AuthenticationManager jwtAdminAuthenticationManager;

	@Autowired
	private UserDetailsService jwtAdminDetailService;

	@Autowired
	private ModelMapper modelMapper;

	/**
	 * Authenticate a user using username & password
	 * 
	 * @param authRequest
	 * @return
	 * @throws AuthenticationException
	 */
	@PostMapping("/users/authenticate")
	public ResponseEntity<?> generateToken(@RequestBody AuthRequestDto authRequest) throws AuthenticationException {
		final String token;
		Authentication authentication = null;

		authentication = jwtAdminAuthenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(authRequest.getEmail(), authRequest.getPassword()));

		if (authentication == null) {
			LOGGER.error("Bad credentials...");
			throw new BadCredentialsException("Bad credentials");
		}

		SecurityContextHolder.getContext().setAuthentication(authentication);

		// Reload password post-security so we can generate token
		final User userDetails = (User) jwtAdminDetailService.loadUserByUsername(authRequest.getEmail());
		if (userDetails == null) {
			LOGGER.error("Username not found...");
			throw new UsernameNotFoundException("Username not found!");
		}
		token = jwtUtil.generateToken(userDetails);

		JwtResponse jwtResponse = new JwtResponse();
		jwtResponse.setId(userDetails.getId());
		jwtResponse.setUsername(userDetails.getUsername());
		jwtResponse.setEmail(userDetails.getEmail());
		jwtResponse.setAccessToken(token);
		return createResponse(HttpStatusCodes.SUCCESS_OK, UserMessages.LOGIN_SUCCESS, null, 0, jwtResponse);

	}

	/**
	 * get current user
	 * 
	 * @param principal
	 * @return
	 * @throws Exception
	 */
	@GetMapping("/users/current-user")
	public ResponseEntity<Response> getCurrentUser(Principal principal) throws Exception {
		User user = (User) jwtAdminDetailService.loadUserByUsername(principal.getName());
		Set<String> roles = (Set<String>) user.getAuthorities().stream().map(item -> item.getAuthority())
				.collect(Collectors.toSet());
		UserDto userDto = new UserDto();
		userDto = modelMapper.map(user, UserDto.class);
		userDto.setRole(roles);
		LOGGER.info("Current logged user..." + user.getUsername());
		return createResponse(HttpStatusCodes.SUCCESS_OK, UserMessages.SUCCESS, null, 0, userDto);
	}

	@PreAuthorize("hasAuthority('SUPERADMIN')")
	@GetMapping(value = "/users/welcome")
	public ResponseEntity<String> welcome() {
		return ResponseEntity.status(HttpStatus.OK).body("welcome in admin");
	}
}
