package com.capgemini.complaint_management_system.DTO;

import java.io.Serializable;
import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ComplainHistoryDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String departmentName;
	private String complaintType;
	private String description;
	private String status;
	private String severity;
	private LocalDate dateFiled;

}