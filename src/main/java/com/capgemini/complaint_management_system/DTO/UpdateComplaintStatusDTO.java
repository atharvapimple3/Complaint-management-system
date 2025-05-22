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
public class UpdateComplaintStatusDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer complainId;
	private Integer userId;
	private String name;

	private String deptName;
	private String complainType;
	private String severity;
	private String description;
	private LocalDate dateFiled;
	private String status;

}
