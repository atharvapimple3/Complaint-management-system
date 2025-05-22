package com.capgemini.complaint_management_system.entities;

import java.io.Serializable;
import java.util.Objects;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
public class ComplaintType implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer ctid;

	@NotBlank(message = "Complain Type cannot be blank")
	@Size(min = 3, max = 50, message = "Enter valid complain type")
	private String complainType;

	// @NotBlank(message = "Department ID cannot be blank")
	private Integer deptId;

	@NotBlank(message = "Severity cannot be empty")
	@Size(min = 3, max = 6)
	private String severity;

	public ComplaintType() {

	}

	public ComplaintType(
			@NotBlank(message = "Complain Type cannot be blank") @Size(min = 3, max = 50, message = "Enter valid complain type") String complainType,
			@NotBlank(message = "Department ID cannot be blank") Integer deptId,
			@NotBlank(message = "Severity cannot be empty") @Size(min = 3, max = 6) String severity) {
		this.complainType = complainType;
		this.deptId = deptId;
		this.severity = severity;
	}

	public ComplaintType(Integer ctid,
			@NotBlank(message = "Complain Type cannot be blank") @Size(min = 3, max = 50, message = "Enter valid complain type") String complainType,
			@NotBlank(message = "Department ID cannot be blank") Integer deptId,
			@NotBlank(message = "Severity cannot be empty") @Size(min = 3, max = 6) String severity) {
		super();
		this.ctid = ctid;
		this.complainType = complainType;
		this.deptId = deptId;
		this.severity = severity;
	}

	public Integer getCtid() {
		return ctid;
	}

	public void setCtid(Integer ctid) {
		this.ctid = ctid;
	}

	public String getComplainType() {
		return complainType;
	}

	public void setComplainType(String complainType) {
		this.complainType = complainType;
	}

	public Integer getDeptId() {
		return deptId;
	}

	public void setDeptId(Integer deptId) {
		this.deptId = deptId;
	}

	public String getSeverity() {
		return severity;
	}

	public void setSeverity(String severity) {
		this.severity = severity;
	}

	@Override
	public int hashCode() {
		return Objects.hash(complainType, ctid, deptId, severity);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ComplaintType other = (ComplaintType) obj;
		return Objects.equals(complainType, other.complainType) && Objects.equals(ctid, other.ctid)
				&& Objects.equals(deptId, other.deptId) && Objects.equals(severity, other.severity);
	}

	@Override
	public String toString() {
		return "ComplaintType [ctid=" + ctid + ", complainType=" + complainType + ", deptid=" + deptId + ", severity="
				+ severity + "]";
	}

}
