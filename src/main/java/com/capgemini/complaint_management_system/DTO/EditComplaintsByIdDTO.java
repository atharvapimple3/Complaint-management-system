package com.capgemini.complaint_management_system.DTO;

import java.io.Serializable;
import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EditComplaintsByIdDTO implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private int userId;
	private String name;
	private String deptName;
	private String complainType;
	private String status;
	private int complainId;
	private String description;
	private String severity;

}
