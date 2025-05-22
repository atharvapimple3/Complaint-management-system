package com.capgemini.complaint_management_system.DTO;

import java.io.Serializable;
import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
public class ApproveComplaintsDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer complainId;
	private String name;
	private Integer userId;
	private String deptName;
	private String complainType;
	private String severity;
	private String description;
	private LocalDate dateFiled;

}
