package com.capgemini.complaint_management_system.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.capgemini.complaint_management_system.entities.User;

@Repository
public interface UserRepo extends JpaRepository<User, Integer> {
	
	boolean existsByEmail(String email);
	
	Optional<User> findByEmail(String email);

	
	
}
