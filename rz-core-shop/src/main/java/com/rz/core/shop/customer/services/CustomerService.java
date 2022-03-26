package com.rz.core.shop.customer.services;

import java.util.List;

import org.apache.tomcat.websocket.AuthenticationException;

import com.rz.core.shop.customer.dto.CustomerDto;
import com.rz.core.shop.customer.model.Customer;

public interface CustomerService {

	String authenticatedCustomer() throws AuthenticationException;
	
	//Customer getCustomerDataByUserName(long id) throws Exception;

	CustomerDto createCustomer(CustomerDto dto) throws Exception;

	public boolean checkIfCustomerExists(final String username) throws Exception;

	public boolean checkIfEmailExists(final String email) throws Exception;

	CustomerDto getCustomerById(long id) throws Exception;

	List<CustomerDto> getCustomer() throws Exception;

	public void deleteCustomerById(long id) throws Exception;

	CustomerDto updateUser(CustomerDto dto, long id) throws Exception;

}
