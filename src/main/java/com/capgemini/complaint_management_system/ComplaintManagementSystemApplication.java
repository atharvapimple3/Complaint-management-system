package com.capgemini.complaint_management_system;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class ComplaintManagementSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(ComplaintManagementSystemApplication.class, args);
	}

}
