package com.rz.core.shop.user.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.rz.core.shop.user.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	@Query("SELECT u FROM User u WHERE (u.username = :username or u.email = :username)")
	User findOneByusername(@Param("username") String username);

	User findByUsername(String username);

	User findByEmail(String email);
}
