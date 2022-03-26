package com.rz.core.shop.customer.controller;

import java.security.Principal;
import java.util.Set;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
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
import com.rz.core.shop.customer.dto.CustomerDto;
import com.rz.core.shop.customer.model.Customer;
import com.rz.core.shop.customer.services.CustomerService;
import com.rz.core.shop.customer.services.impl.CustomerServiceImpl;
import com.rz.core.shop.security.common.config.JwtUtils;
import com.rz.core.shop.user.controller.AuthenticateUserApiController;
import com.rz.core.shop.user.dto.AuthRequestDto;
import com.rz.core.shop.user.dto.JwtResponse;
import com.rz.core.shop.user.dto.UserDto;
import com.rz.core.shop.user.model.User;
import com.rz.core.shop.user.services.CustomUserDetailsService;
import com.rz.core.shop.user.services.UserService;

/**
 * @author ravi
 *
 */
@CrossOrigin(origins = "*")
@RestController
@RequestMapping(value = "/api/v1")
@SuppressWarnings({ "unused" })
public class AuthenticateCustomerApi extends BaseController {

	private static final Logger LOGGER = LoggerFactory.getLogger(AuthenticateUserApiController.class);

	@Autowired
	private JwtUtils jwtUtil;

	@Autowired
	private AuthenticationManager jwtCustomerAuthenticationManager;

	@Autowired
	private CustomUserDetailsService jwtCustomerDetailService;

	@Autowired
	private CustomerServiceImpl customerService;

	@Autowired
	private UserDetailsService jwtAdminDetailService;

	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private UserService userService;

	/**
	 * Authenticate a customer using username & password
	 * 
	 * @param authRequest
	 * @return
	 * @throws Exception
	 */
	@PostMapping("/customers/authenticate")
	public ResponseEntity<?> generateToken(@RequestBody AuthRequestDto authRequest) throws AuthenticationException {
		final String token;
		Authentication authentication = null;

		authentication = jwtCustomerAuthenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(authRequest.getEmail(), authRequest.getPassword()));

		if (authentication == null) {
			LOGGER.info("Bad credentials...");
			throw new BadCredentialsException("Bad credentials");
		}

		SecurityContextHolder.getContext().setAuthentication(authentication);

		// Reload password post-security so we can generate token
		final User userDetails = (User) jwtAdminDetailService.loadUserByUsername(authRequest.getEmail());

		token = jwtUtil.generateToken(userDetails);

		JwtResponse jwtResponse = new JwtResponse();
		jwtResponse.setId(userDetails.getId());
		jwtResponse.setUsername(userDetails.getUsername());
		jwtResponse.setEmail(userDetails.getEmail());
		jwtResponse.setAccessToken(token);
		LOGGER.info("login success with token!");
		return createResponse(HttpStatusCodes.SUCCESS_OK, UserMessages.LOGIN_SUCCESS, null, 0, jwtResponse);

	}

	/**
	 * Create a new customer
	 * 
	 * @param userDto
	 * @return
	 * @throws Exception
	 */

	@PostMapping(value = "/customers/register")
	public ResponseEntity<Response> createUser(@Valid @RequestBody UserDto userDto) throws Exception {
		boolean username = userService.checkIfUserExists(userDto.getUsername());
		boolean email = userService.checkIfEmailExists(userDto.getEmail());
		UserDto dto = userService.createUser(userDto);
		LOGGER.info("Customer register...");
		return createResponse(HttpStatusCodes.SUCCESS_CREATED, UserMessages.USER_CREATED, null, 0, dto);
	}

	/*
	 * @PostMapping(value = "/customers/register") public ResponseEntity<Response>
	 * createUser(@Valid @RequestBody CustomerDto customerDto) throws Exception {
	 * boolean username =
	 * customerService.checkIfCustomerExists(customerDto.getNick()); boolean email =
	 * customerService.checkIfEmailExists(customerDto.getEmail()); CustomerDto dto =
	 * customerService.createCustomer(customerDto);
	 * LOGGER.info("Customer register..."); return
	 * createResponse(HttpStatusCodes.SUCCESS_CREATED, UserMessages.USER_CREATED,
	 * null, 0, dto); }
	 */

	/**
	 * current logged customer
	 * 
	 * @param principal
	 * @return
	 * @throws Exception
	 */
	@GetMapping("/customers/current-user")
	public ResponseEntity<Response> getCurrentUser(Principal principal) throws Exception {
		User user = (User) jwtCustomerDetailService.loadUserByUsername(principal.getName());
		Set<String> roles = (Set<String>) user.getAuthorities().stream().map(item -> item.getAuthority())
				.collect(Collectors.toSet());
		UserDto userDto = modelMapper.map(user, UserDto.class);
		userDto.setRole(roles);
		LOGGER.info("Current logged user..." + user.getUsername());
		return createResponse(HttpStatusCodes.SUCCESS_OK, UserMessages.SUCCESS, null, 0, userDto);
	}

	@GetMapping(value = "/customers/welcome")
	public ResponseEntity<String> welcome() {
		return ResponseEntity.status(HttpStatus.OK).body("welcome in customer");
	}

}
