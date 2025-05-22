package com.capgemini.complaint_management_system.DTO;

import java.io.Serializable;
import java.time.LocalDate;

public class LatestComplaintDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private String userName;
	private Integer complainId;
	private String departmentName;
	private String description;
	private String status;
	private LocalDate dateFiled;

	public LatestComplaintDTO() {

	}

	public LatestComplaintDTO(String userName, Integer complainId, String departmentName, String description,
			String status, LocalDate dateFiled) {
		super();
		this.userName = userName;
		this.complainId = complainId;
		this.departmentName = departmentName;
		this.description = description;
		this.status = status;
		this.dateFiled = dateFiled;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Integer getComplainId() {
		return complainId;
	}

	public void setComplainId(Integer complainId) {
		this.complainId = complainId;
	}

	public String getDepartmentName() {
		return departmentName;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public LocalDate getDateFiled() {
		return dateFiled;
	}

	public void setDateFiled(LocalDate dateFiled) {
		this.dateFiled = dateFiled;
	}

}
