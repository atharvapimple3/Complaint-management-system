package com.capgemini.complaint_management_system.exception;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(DuplicateEmailException.class)
	public ResponseEntity<Object> handleDuplicateEmailExceptions(DuplicateEmailException e) {
		Map<String, Object> errors = new HashMap<String, Object>();
		errors.put("time-stamp", LocalDateTime.now());
		errors.put("Status", HttpStatus.CONFLICT.value());
		errors.put("details", e.getMessage());
		return new ResponseEntity<Object>(errors, HttpStatus.CONFLICT);
	}

	@ExceptionHandler(UserNotFoundException.class)
	public ResponseEntity<Object> handleUserNotFound(UserNotFoundException ex) {
		Map<String, Object> errors = new HashMap<String, Object>();
		errors.put("time-stamp", LocalDateTime.now());
		errors.put("Status", HttpStatus.NOT_FOUND.value());
		errors.put("details", ex.getMessage());
		return new ResponseEntity<Object>(errors, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(DepartmentNotFoundException.class)
	public ResponseEntity<Object> handleDepartmentNotFound(DepartmentNotFoundException ex) {
		Map<String, Object> errors = new HashMap<String, Object>();
		errors.put("Timestamp", LocalDateTime.now());
		errors.put("Status", HttpStatus.NOT_FOUND.value());
		errors.put("Description", ex.getMessage());
		return new ResponseEntity<Object>(errors, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(EmailNotFoundException.class)
	public ResponseEntity<Object> EmailNotFound(EmailNotFoundException ex) {
		Map<String, Object> errors = new HashMap<String, Object>();
		errors.put("time-stamp", LocalDateTime.now());
		errors.put("Status", HttpStatus.NOT_FOUND.value());
		errors.put("details", ex.getMessage());
		return new ResponseEntity<Object>(errors, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(ComplaintTypeNotFoundException.class)
	public ResponseEntity<Object> handleComplainTypeNotFound(ComplaintTypeNotFoundException ex) {
		Map<String, Object> errors = new HashMap<String, Object>();
		errors.put("Timestamp", LocalDateTime.now());
		errors.put("Status", HttpStatus.NOT_FOUND.value());
		errors.put("Description", ex.getMessage());
		return new ResponseEntity<Object>(errors, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(ComplaintNotFoundException.class)
	public ResponseEntity<Object> handleComplainNotFound(ComplaintNotFoundException ex) {
		Map<String, Object> errors = new HashMap<String, Object>();
		errors.put("Timestamp", LocalDateTime.now());
		errors.put("Status", HttpStatus.NOT_FOUND.value());
		errors.put("Description", ex.getMessage());
		return new ResponseEntity<Object>(errors, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<Object> handleAllOtherExceptions(Exception e) {
		Map<String, Object> errors = new HashMap<String, Object>();
		errors.put("time-stamp", LocalDateTime.now());
		errors.put("Status", HttpStatus.NOT_FOUND.value());
		errors.put("details", e.getMessage());
		return new ResponseEntity<Object>(errors, HttpStatus.NOT_FOUND);
	}

}
