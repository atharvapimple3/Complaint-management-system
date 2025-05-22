package com.capgemini.complaint_management_system.service;

import java.util.List;

import com.capgemini.complaint_management_system.entities.ComplaintType;

public interface ComplainTypeService {

	ComplaintType addComplaintType(ComplaintType complaintType);

	ComplaintType updateComplaintType(Integer id, ComplaintType complaintType);

	ComplaintType findComplaintType(Integer id);

	boolean deleteComplainType(Integer id);

	List<ComplaintType> findAllComplainTypes();

	List<ComplaintType> findComplaintTypesByDeptId(Integer deptId);
}
