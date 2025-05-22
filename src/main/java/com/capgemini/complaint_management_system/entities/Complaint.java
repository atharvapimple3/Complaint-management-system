package com.capgemini.complaint_management_system.entities;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
public class Complaint implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer complainId;

//	@NotNull(message = "User Id cannot be null")
	private Integer userId;

//	@NotNull(message = "Department Id cannot be null")
	private Integer deptId;

//	@NotNull(message = "Complain Id cannot be null")
	private Integer ctid;

	@NotBlank(message = "Description cannot be blank")
	private String description;

	@DateTimeFormat
	private LocalDate dateFiled;

	@NotBlank(message = "Status cannot be blank")
	private String status;

	public Complaint() {

	}

	public Complaint(@NotNull(message = "User Id cannot be null") Integer userId,
			@NotNull(message = "Department Id cannot be null") Integer deptId,
			@NotNull(message = "Complain Id cannot be null") Integer ctid,
			@NotBlank(message = "Description cannot be blank") String description, LocalDate dateFiled,
			@NotBlank(message = "Status cannot be blank") String status) {
		this.userId = userId;
		this.deptId = deptId;
		this.ctid = ctid;
		this.description = description;
		this.dateFiled = dateFiled;
		this.status = status;
	}

	public Complaint(Integer complainId, @NotNull(message = "User Id cannot be null") Integer userId,
			@NotNull(message = "Department Id cannot be null") Integer deptId,
			@NotNull(message = "Complain Id cannot be null") Integer ctid,
			@NotBlank(message = "Description cannot be blank") String description, LocalDate dateFiled,
			@NotBlank(message = "Status cannot be blank") String status) {
		super();
		this.complainId = complainId;
		this.userId = userId;
		this.deptId = deptId;
		this.ctid = ctid;
		this.description = description;
		this.dateFiled = dateFiled;
		this.status = status;
	}

	public Integer getComplainId() {
		return complainId;
	}

	public void setComplainId(Integer complainId) {
		this.complainId = complainId;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getDeptId() {
		return deptId;
	}

	public void setDeptId(Integer deptId) {
		this.deptId = deptId;
	}

	public Integer getCtid() {
		return ctid;
	}

	public void setCtid(Integer ctid) {
		this.ctid = ctid;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public LocalDate getDateFiled() {
		return dateFiled;
	}

	public void setDateFiled(LocalDate dateFiled) {
		this.dateFiled = dateFiled;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public int hashCode() {
		return Objects.hash(complainId, ctid, dateFiled, deptId, description, status, userId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Complaint other = (Complaint) obj;
		return Objects.equals(complainId, other.complainId) && Objects.equals(ctid, other.ctid)
				&& Objects.equals(dateFiled, other.dateFiled) && Objects.equals(deptId, other.deptId)
				&& Objects.equals(description, other.description) && Objects.equals(status, other.status)
				&& Objects.equals(userId, other.userId);
	}

	@Override
	public String toString() {
		return "Complaint [complainId=" + complainId + ", userId=" + userId + ", deptId=" + deptId + ", ctid=" + ctid
				+ ", description=" + description + ", dateFiled=" + dateFiled + ", status=" + status + "]";
	}

}
