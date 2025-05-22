package com.capgemini.complaint_management_system.service;

import java.time.LocalDate;
import java.util.List;

import com.capgemini.complaint_management_system.DTO.*;
import com.capgemini.complaint_management_system.entities.Complaint;

public interface ComplaintService {

	Complaint addComplaint(Complaint complaint);

	Complaint updateComplaint(Integer id, Complaint complaint);

	Complaint findComplaint(Integer id);

	boolean deleteComplaint(Integer id);

	List<Complaint> findAllComplaints();
	
	List<ComplainHistoryDTO> findComplaintsByQuery(Integer userId);
	
	List<ApproveComplaintsDTO> getComplaintsToApprove();
	
	List<AllComplaintsDTO> getAllComplains();

	List<UpdateComplaintStatusDTO> getComplaintsToUpdateStatus();
	
	List<EditComplaintsByIdDTO> getComplaintsToEdit(Integer complainId);

	List<AllComplaintsDTO> getComplintsFiledBetween(LocalDate start, LocalDate end);
	
}
