package com.capgemini.complaint_management_system.exception;

public class DuplicateEmailException extends RuntimeException {

	public DuplicateEmailException(String message) {
		super(message);
	}

}
