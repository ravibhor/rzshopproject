package com.rz.core.shop.customer.services.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.tomcat.websocket.AuthenticationException;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.rz.core.shop.base.constants.Constants;
import com.rz.core.shop.base.enums.StatusEnum;
import com.rz.core.shop.customer.dto.CustomerDto;
import com.rz.core.shop.customer.model.Customer;
import com.rz.core.shop.customer.respository.CustomerRepository;
import com.rz.core.shop.customer.services.CustomerService;
import com.rz.core.shop.user.model.Role;
import com.rz.core.shop.user.repositories.RoleRepository;

@Service
public class CustomerServiceImpl implements CustomerService, UserDetailsService {

	@Autowired
	private CustomerRepository customerRepository;

	private static final Logger LOGGER = LoggerFactory.getLogger(CustomerServiceImpl.class);

	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private PasswordEncoder encoder;

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public String authenticatedCustomer() throws AuthenticationException {
		return null;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Customer user = customerRepository.findOneByusername(username);
		//LOGGER.warn("customer="+user.getEmail().toString());
		if (user == null) {
			LOGGER.error("username not found", username);
			throw new UsernameNotFoundException("Username not found ");
		}
		return (UserDetails) user;
	}

	@Override
	public CustomerDto createCustomer(CustomerDto dto) throws Exception {
		Customer customer = new Customer();
		BeanUtils.copyProperties(dto, customer);
		customer.setStatus(StatusEnum.ACTIVE);

		Set<String> strRoles = dto.getRole();

		Set<Role> role = new HashSet<>();

		if (strRoles != null) {
			Role customerRole = roleRepository.findByRoleName(Constants.ROLE_CUSTOMER).get();
			role.add(customerRole);
		}
		// set roles to customer
		Role roles = new Role();
		customer.getCustomerRoles().addAll(role);
		roles.getCustomer().add(customer);

		// save user
		customer.setPassword(encoder.encode(customer.getPassword()));
		customer = customerRepository.save(customer);
		CustomerDto custDto = modelMapper.map(customer, CustomerDto.class);
		return custDto;
	}

	@Override
	public boolean checkIfCustomerExists(String username) throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean checkIfEmailExists(String email) throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public CustomerDto getCustomerById(long id) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<CustomerDto> getCustomer() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteCustomerById(long id) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public CustomerDto updateUser(CustomerDto dto, long id) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}
