package com.capgemini.complaint_management_system.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionSystemException;

import com.capgemini.complaint_management_system.entities.User;
import com.capgemini.complaint_management_system.exception.DuplicateEmailException;
import com.capgemini.complaint_management_system.exception.UserNotFoundException;
import com.capgemini.complaint_management_system.repository.UserRepo;

@Service
public class UserServiceImpl implements UserService {

	UserRepo userRepo;

	@Autowired
	public UserServiceImpl(UserRepo userRepo) {
		this.userRepo = userRepo;
	}

	@Override
	@CacheEvict(value = "users" ,allEntries = true)
	public User addUser(User user) {
		String email = user.getEmail();
		if (userRepo.existsByEmail(email)) {
			throw new DuplicateEmailException("Email already exists" + email);
		}
		return userRepo.save(user);
	}

	@Override
	@CacheEvict(value = "users" ,allEntries = true)
	public User updateUser(Integer id, User user) {
		User existing = userRepo.findById(id)
				.orElseThrow(() -> new UserNotFoundException("User doesn't exists with ID: " + id));
		if (!existing.getEmail().equals(user.getEmail())) {
			if (userRepo.existsByEmail(user.getEmail())) {
				throw new DuplicateEmailException("Email already registered!");
			}
		}
		existing.setEmail(user.getEmail());
		existing.setPassword(user.getPassword());
		existing.setUserType(user.getUserType());
		existing.setPhone(user.getPhone());
		try {
			return userRepo.save(existing);
		} catch (DataIntegrityViolationException e) {
			throw new DuplicateEmailException("Email already exists: " + user.getEmail());
		} catch (TransactionSystemException e) {
			throw new RuntimeException("Database error occurred while updating user", e);
		}
	}

	@Override
	@Cacheable(value = "userById")
	public User findUser(Integer id) {
		return userRepo.findById(id).orElseThrow(() -> new UserNotFoundException("User doesn't exist with ID: " + id));

	}

	@Override
	@Cacheable(value = "users")
	public List<User> findAllUsers() {
		return userRepo.findAll();
	}

	@Override
	@CacheEvict(value = "users" ,allEntries = true)
	public boolean deleteUser(Integer id) {
		userRepo.findById(id).orElseThrow(() -> new UserNotFoundException("User doesn't exists with ID: " + id));
		userRepo.deleteById(id);
		return true;
	}

	@Override
	public boolean existsByEmail(String email) {
		return userRepo.existsByEmail(email);
	}

	@Override
	public User findByEmail(String email) {
		return userRepo.findByEmail(email)
				.orElseThrow(() -> new UserNotFoundException("User doesn't exists with Email :" + email));
	}

}
