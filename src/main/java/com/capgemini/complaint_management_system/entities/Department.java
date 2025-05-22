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
public class Department implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer deptId;

	@NotBlank(message = "Enter valid dept name")
	@Size(min = 3, max = 20)
	private String deptName;

	//@NotBlank(message = "Enter valid contact")
	private Long contact;

	public Department() {

	}

	public Department(@NotBlank(message = "Enter valid dept name") @Size(min = 3, max = 20) String deptName,
			@NotBlank(message = "Enter valid contact") Long contact) {
		this.deptName = deptName;
		this.contact = contact;
	}

	public Department(Integer deptId,
			@NotBlank(message = "Enter valid dept name") @Size(min = 3, max = 20) String deptName,
			@NotBlank(message = "Enter valid contact") Long contact) {
		super();
		this.deptId = deptId;
		this.deptName = deptName;
		this.contact = contact;
	}

	public Integer getDeptId() {
		return deptId;
	}

	public void setDeptId(Integer deptId) {
		this.deptId = deptId;
	}

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	public Long getContact() {
		return contact;
	}

	public void setContact(Long contact) {
		this.contact = contact;
	}

	@Override
	public int hashCode() {
		return Objects.hash(contact, deptId, deptName);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Department other = (Department) obj;
		return Objects.equals(contact, other.contact) && Objects.equals(deptId, other.deptId)
				&& Objects.equals(deptName, other.deptName);
	}

	@Override
	public String toString() {
		return "Departments [deptId=" + deptId + ", deptName=" + deptName + ", contact=" + contact + "]";
	}

}
