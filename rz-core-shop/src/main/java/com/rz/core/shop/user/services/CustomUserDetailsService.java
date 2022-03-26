package com.rz.core.shop.user.services;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.EnumType;

import org.apache.tomcat.websocket.AuthenticationException;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.rz.core.shop.base.constants.Constants;
import com.rz.core.shop.base.constants.UserErrorConstants;
import com.rz.core.shop.base.enums.StatusEnum;
import com.rz.core.shop.exceptions.UserApiException;
import com.rz.core.shop.user.dto.RolesDto;
import com.rz.core.shop.user.dto.UserDto;
import com.rz.core.shop.user.dto.UserRoleDto;
import com.rz.core.shop.user.model.Role;
import com.rz.core.shop.user.model.User;
import com.rz.core.shop.user.model.UserRole;
import com.rz.core.shop.user.model.UserRoleRepository;
import com.rz.core.shop.user.repositories.RoleRepository;
import com.rz.core.shop.user.repositories.UserRepository;

@Primary
@Service
@SuppressWarnings({ "unused" })
public class CustomUserDetailsService implements UserDetailsService, UserService {

	private static final Logger LOGGER = LoggerFactory.getLogger(CustomUserDetailsService.class);

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private UserRoleRepository userRoleRepository;

	@Autowired
	private PasswordEncoder encoder;

	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private CustomUserDetailsService jwtUserDetailService;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.findOneByusername(username);
		if (user == null) {
			LOGGER.error("username not found", username);
			throw new UsernameNotFoundException("Username not found ");
		}
		return (UserDetails) user;
	}

	@Override
	public UserDto createUser(UserDto userDto) throws Exception {
		User user = new User();
		BeanUtils.copyProperties(userDto, user);
		user.setStatus(StatusEnum.ACTIVE);
		Set<UserRole> roles = new HashSet<UserRole>();
		UserRole userRole = new UserRole();
		userRole.setUser(user);
		Set<String> strRoles = userDto.getRole();

		if (strRoles == null) {
			Role customerRole = roleRepository.findByRoleName(Constants.ROLE_CUSTOMER).get();
			userRole.setRole(customerRole);
		} else {
			strRoles.forEach(role -> {
				switch (role) {
				case Constants.ROLE_SUPERADMIN:
					Role superAdminRole = roleRepository.findByRoleName(Constants.ROLE_SUPERADMIN)
							.orElseThrow(() -> new UserApiException(UserErrorConstants.ROLE_NOT_FOUND));
					userRole.setRole(superAdminRole);
					break;
				case Constants.ROLE_ADMIN:
					Role adminRole = roleRepository.findByRoleName(Constants.ROLE_ADMIN)
							.orElseThrow(() -> new UserApiException(UserErrorConstants.ROLE_NOT_FOUND));
					userRole.setRole(adminRole);
					break;	

				default:
					Role customerRole = roleRepository.findByRoleName(Constants.ROLE_CUSTOMER)
							.orElseThrow(() -> new UserApiException(UserErrorConstants.ROLE_NOT_FOUND));
					userRole.setRole(customerRole);
				}
			});
		}
		roles.add(userRole);
		user.getUserRoles().addAll(roles);

		// save user
		user.setPassword(encoder.encode(user.getPassword()));
		user = userRepository.save(user);
		UserDto dto = modelMapper.map(user, UserDto.class);
		return dto;
	}

	@Override
	public boolean checkIfUserExists(String username) throws Exception {
		User user = userRepository.findByUsername(username);
		if (user != null) {
			LOGGER.info("username already exists!", username);
			throw new UserApiException(UserErrorConstants.USERNAME_ALREADY_EXISTS);
		}
		return false;
	}

	@Override
	public boolean checkIfEmailExists(String email) throws Exception {
		User user = userRepository.findByEmail(email);
		if (user != null) {
			LOGGER.info("Email already exists!", email);
			throw new UserApiException(UserErrorConstants.EMAIL_ALREADY_EXIST);
		}
		return false;
	}

	@Override
	public String authenticatedUser() throws AuthenticationException {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

		if (authentication == null) {
			throw new UserApiException(UserErrorConstants.USER_UNAUTHORIZED);
		}

		if (!(authentication instanceof AnonymousAuthenticationToken)) {
			String currentUserName = authentication.getName();
			User user=(User) jwtUserDetailService.loadUserByUsername(currentUserName);
			Long id=user.getId();
			String currentUserId=String.valueOf(id); 
			return currentUserId;
		}
		return null;
	}

	@Override
	public UserDto getUserById(long id) throws Exception {
		Optional<User> user = Optional.ofNullable(
				userRepository.findById(id).orElseThrow(() -> new UserApiException(UserErrorConstants.USER_NOT_FOUND)));
		Optional<UserRole> userRole = userRoleRepository.findById(id);
		Role role=userRole.get().getRole();
		
		UserDto dto = modelMapper.map(user.get(), UserDto.class);
		RolesDto rolesDto=modelMapper.map(userRole.get().getRole(), RolesDto.class);
		dto.setRoles(rolesDto);
		return dto;
	}

	@Override
	public void deleteUserById(long id) throws Exception {
		Optional<User> user = Optional.ofNullable(
				userRepository.findById(id).orElseThrow(() -> new UserApiException(UserErrorConstants.USER_NOT_FOUND)));
		user.get().setEnabled(false);
		userRepository.save(user.get());
	}

	@Override
	public UserDto updateUser(UserDto dto, long id) throws Exception {

		Optional<User> getUser = userRepository.findById(id);
		if (!getUser.isPresent()) {
			LOGGER.error("User not found with id: '{}'", id);
			throw new UserApiException(UserErrorConstants.USER_NOT_FOUND);
		}
		User user = new User();
		/*
		 * Set<UserRole> roles = new HashSet<UserRole>(); UserRole userRole = new
		 * UserRole(); roles = getUser.get().getUserRoles();
		 * 
		 * User user=new User(); userRole.setUser(user); for (UserRole userRole2 :
		 * roles) { userRole.setRole(userRole2.getRole()); } roles.add(userRole);
		 */

		BeanUtils.copyProperties(dto, user);
		user.setId(getUser.get().getId());
		user.setPassword(encoder.encode(user.getPassword()));
		user = userRepository.save(user);
		dto = modelMapper.map(user, UserDto.class);
		return dto;
	}

	@Override
	public List<UserDto> getUsers() throws Exception {
		List<UserDto> list=new ArrayList<UserDto>();
		List<User> user = userRepository.findAll();
		
		for (User usr : user) {
			UserDto dto = modelMapper.map(usr, UserDto.class);
			Optional<UserRole> userRole = userRoleRepository.findById(usr.getId());
			Role role=userRole.get().getRole();
			RolesDto rolesDto=modelMapper.map(userRole.get().getRole(), RolesDto.class);
			dto.setRoles(rolesDto);
			list.add(dto);
		}
		return list;
	}

}
