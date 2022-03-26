package com.rz.core.shop.customer.respository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.rz.core.shop.customer.model.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

	@Query("SELECT u FROM Customer u WHERE (u.username = :username or u.email = :username)")
	Customer findOneByusername(@Param("username") String username);
}
