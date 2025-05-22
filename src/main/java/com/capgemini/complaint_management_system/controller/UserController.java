package com.capgemini.complaint_management_system.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.capgemini.complaint_management_system.DTO.LatestComplaintDTO;
import com.capgemini.complaint_management_system.entities.User;
import com.capgemini.complaint_management_system.service.DTOService;
import com.capgemini.complaint_management_system.service.UserService;

@RestController
@RequestMapping("/api/users")
public class UserController {

	UserService userService;
	DTOService dtoService;

	@Autowired
	public UserController(UserService userService, DTOService dtoService) {
		this.userService = userService;
		this.dtoService = dtoService;
	}

	@GetMapping
	public ResponseEntity<List<User>> getAllUsers() {
		return ResponseEntity.status(HttpStatus.OK).body(userService.findAllUsers());
	}

	@GetMapping("/{id}/latest-complaint")
	public ResponseEntity<LatestComplaintDTO> getLatestComplaint(@PathVariable("id") Integer id) {
		return ResponseEntity.status(HttpStatus.OK).body(dtoService.getLatestComplaintInfo(id));
	}

	@GetMapping("/{id}")
	public ResponseEntity<User> getUser(@PathVariable("id") Integer id) {
		return ResponseEntity.status(HttpStatus.OK).body(userService.findUser(id));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<User> deleteUser(@PathVariable Integer id) {
		userService.deleteUser(id);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}

//	@PostMapping("/register")
//	public ResponseEntity<User> addUser(@RequestBody User user) {
//		return ResponseEntity.status(HttpStatus.CREATED).body(userService.addUser(user));
//	}

	@PutMapping("/{id}")
	public ResponseEntity<User> updateUser(@PathVariable("id") Integer id, @RequestBody User user) {
		return ResponseEntity.status(HttpStatus.OK)
				.body(userService.updateUser(id, user));
	}

}
