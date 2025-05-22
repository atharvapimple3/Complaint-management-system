package com.capgemini.complaint_management_system.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.capgemini.complaint_management_system.DTO.EmailRequest;
import com.capgemini.complaint_management_system.service.EmailService;

@RestController
@RequestMapping("/api/email")
public class EmailController {

	private EmailService emailService;
	
	public EmailController(EmailService emailService) {
		this.emailService = emailService;
	}
	
	@PostMapping("/send")
	 public String sendEmail(@RequestBody EmailRequest emailRequest) {
        emailService.sendMessage(emailRequest.getTo(), emailRequest.getSubject(), emailRequest.getText());
        return "Email sent successfully";
    }

}
