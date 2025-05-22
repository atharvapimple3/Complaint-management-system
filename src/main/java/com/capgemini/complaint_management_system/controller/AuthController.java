package com.capgemini.complaint_management_system.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.capgemini.complaint_management_system.DTO.LoginDto;
import com.capgemini.complaint_management_system.DTO.ResponseToken;
import com.capgemini.complaint_management_system.entities.User;
import com.capgemini.complaint_management_system.exception.DuplicateEmailException;
import com.capgemini.complaint_management_system.repository.UserRepo;
import com.capgemini.complaint_management_system.security.JwtUtils;
import com.capgemini.complaint_management_system.service.UserService;

@RestController
@RequestMapping("/auth")
public class AuthController {

	AuthenticationManager authenticationManager;
	UserService userService;
	PasswordEncoder passwordEncoder;
	JwtUtils jwtService;
	UserRepo userRepo;

	@Autowired
	public AuthController(AuthenticationManager authenticationManager, UserService userService,
			PasswordEncoder passwordEncoder, JwtUtils jwtService, UserRepo userRepo) {
		this.userService = userService;
		this.userRepo = userRepo;
		this.authenticationManager = authenticationManager;
		this.passwordEncoder = passwordEncoder;
		this.jwtService = jwtService;
	}

	@PostMapping("/signin")
	public ResponseEntity<?> authenticateUser(@RequestBody LoginDto loginDto) {
		Authentication authentication = authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(loginDto.getUsername(), loginDto.getPassword()));

		if (authentication.isAuthenticated()) {
			User user = userService.findByEmail(loginDto.getUsername());
			Map<String, Object> claims = new HashMap<>();
			claims.put("email", user.getEmail());
			claims.put("userid", user.getUserId());
			claims.put("usertype", user.getUserType());
			String token = jwtService.generateToken(loginDto.getUsername(), claims);
			ResponseToken responseToken = new ResponseToken(token);
			return ResponseEntity.status(HttpStatus.OK).body(responseToken);
		}
		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("You are not Authorized !!");
	}

	@PostMapping("/register")
	public ResponseEntity<User> registerUser(@RequestBody User user) {
		if (userService.existsByEmail(user.getEmail()))
			throw new DuplicateEmailException("Email Already Exists !");
		user.setPassword(passwordEncoder.encode(user.getPassword()));

		return ResponseEntity.status(HttpStatus.CREATED).body(userService.addUser(user));
	}
}
