package com.capgemini.complaint_management_system.service;

import java.util.List;

import com.capgemini.complaint_management_system.DTO.ApproveComplaintsDTO;
import com.capgemini.complaint_management_system.DTO.ComplainHistoryDTO;
import com.capgemini.complaint_management_system.DTO.ComplaintTypeDTO;
import com.capgemini.complaint_management_system.DTO.LatestComplaintDTO;

public interface DTOService {

	LatestComplaintDTO getLatestComplaintInfo(Integer userId);

//	List<ComplainHistoryDTO> getAllComplains(Integer userId);
	
	List<ComplaintTypeDTO> getComplaintTypes();
	
	

}
