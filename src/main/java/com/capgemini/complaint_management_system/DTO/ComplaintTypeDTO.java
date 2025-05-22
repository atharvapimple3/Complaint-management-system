package com.capgemini.complaint_management_system.DTO;

import java.io.Serializable;
import java.util.List;

import com.capgemini.complaint_management_system.entities.ComplaintType;

public class ComplaintTypeDTO implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private Integer deptId;
	private String deptName;
	private List<ComplaintType> complaintTypes;

	public ComplaintTypeDTO() {
		super();
	}

	public ComplaintTypeDTO(Integer deptId, String deptName, List<ComplaintType> complaintTypes) {
		super();
		this.deptId = deptId;
		this.deptName = deptName;
		this.complaintTypes = complaintTypes;
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

	public List<ComplaintType> getComplaintTypes() {
		return complaintTypes;
	}

	public void setComplaintTypes(List<ComplaintType> complaintTypes) {
		this.complaintTypes = complaintTypes;
	}

	@Override
	public String toString() {
		return "ComplaintTypeDTO [deptId=" + deptId + ", deptName=" + deptName + ", complaintTypes=" + complaintTypes
				+ "]";
	}

}
