package com.capgemini.complaint_management_system.DTO;

public class ResponseToken {
	String token;

	public ResponseToken() {
		super();
	}

	public ResponseToken(String token) {
		super();
		this.token = token;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

}
