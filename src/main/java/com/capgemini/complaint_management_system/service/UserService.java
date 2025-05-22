package com.capgemini.complaint_management_system.service;

import java.util.List;

import com.capgemini.complaint_management_system.entities.User;

public interface UserService {

	User addUser(User user);

	User updateUser(Integer id, User user);

	User findUser(Integer id);

	List<User> findAllUsers();

	boolean deleteUser(Integer id);
	
	boolean existsByEmail(String email);
	
	User findByEmail(String email);

}
