package com.capgemini.complaint_management_system.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EmailRequest {
	
	private String to;
	private String subject;
	private String text;
	
	public EmailRequest() {
		
	}
	
	public EmailRequest(String to, String subject, String text) {
		super();
		this.to = to;
		this.subject = subject;
		this.text = text;
	}
	
	

}
