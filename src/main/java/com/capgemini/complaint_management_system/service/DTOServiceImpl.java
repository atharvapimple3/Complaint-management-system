package com.capgemini.complaint_management_system.service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.capgemini.complaint_management_system.DTO.ComplainHistoryDTO;
import com.capgemini.complaint_management_system.DTO.ComplaintTypeDTO;
import com.capgemini.complaint_management_system.DTO.LatestComplaintDTO;
import com.capgemini.complaint_management_system.entities.Complaint;
import com.capgemini.complaint_management_system.entities.ComplaintType;
import com.capgemini.complaint_management_system.entities.Department;
import com.capgemini.complaint_management_system.entities.User;
import com.capgemini.complaint_management_system.exception.ComplaintTypeNotFoundException;
import com.capgemini.complaint_management_system.exception.DepartmentNotFoundException;
import com.capgemini.complaint_management_system.exception.UserNotFoundException;
import com.capgemini.complaint_management_system.repository.ComplainTypeRepo;
import com.capgemini.complaint_management_system.repository.ComplaintRepo;
import com.capgemini.complaint_management_system.repository.DepartmentRepo;
import com.capgemini.complaint_management_system.repository.UserRepo;

@Service
public class DTOServiceImpl implements DTOService {

	ComplaintRepo complaintRepo;
	UserRepo userRepo;
	DepartmentRepo departmentRepo;
	ComplainTypeRepo complainTypeRepo;

	@Autowired
	public DTOServiceImpl(ComplaintRepo complaintRepo, UserRepo userRepo, DepartmentRepo departmentRepo,
			ComplainTypeRepo complainTypeRepo) {
		super();
		this.complaintRepo = complaintRepo;
		this.userRepo = userRepo;
		this.departmentRepo = departmentRepo;
		this.complainTypeRepo = complainTypeRepo;
	}

	@Override
	@Cacheable(value = "latestComplain")
	public LatestComplaintDTO getLatestComplaintInfo(Integer userId) {

		User user = userRepo.findById(userId)
				.orElseThrow(() -> new UserNotFoundException("User not found with ID: " + userId));

		List<Complaint> complaints = complaintRepo.findByUserId(userId);

		Complaint latest = complaints.stream().max(Comparator.comparing(Complaint::getDateFiled))
				.orElseThrow(() -> new RuntimeException("No date found"));

		Department department = departmentRepo.findById(latest.getDeptId()).orElseThrow(
				() -> new DepartmentNotFoundException("Department not found with ID: " + latest.getDeptId()));

		LatestComplaintDTO latestComplain = new LatestComplaintDTO();

		latestComplain.setComplainId(latest.getComplainId());
		latestComplain.setUserName(user.getName());
		latestComplain.setDepartmentName(department.getDeptName());
		latestComplain.setDateFiled(latest.getDateFiled());
		latestComplain.setDescription(latest.getDescription());
		latestComplain.setStatus(latest.getStatus());

		return latestComplain;

	}

//	@Override
//	public List<ComplainHistoryDTO> getAllComplains(Integer userId) {
//
//		User user = userRepo.findById(userId).orElseThrow(() -> new UserNotFoundException("User not found"));
//
//		List<Complaint> complaints = complaintRepo.findByUserId(userId);
//
//		List<ComplainHistoryDTO> dto = new ArrayList<ComplainHistoryDTO>();
//
//		for (Complaint c : complaints) {
//			Department dept = departmentRepo.findById(c.getDeptId())
//					.orElseThrow(() -> new DepartmentNotFoundException("Department not found"));
//
//			ComplaintType complaintType = complainTypeRepo.findById(c.getCtid())
//					.orElseThrow(() -> new ComplaintTypeNotFoundException("Complain Type not found"));
//
//			ComplainHistoryDTO complainHistoryDTO = new ComplainHistoryDTO();
//			complainHistoryDTO.setDepartmentName(dept.getDeptName());
//			complainHistoryDTO.setComplaintType(complaintType.getComplainType());
//			complainHistoryDTO.setDateFiled(c.getDateFiled());
//			complainHistoryDTO.setStatus(c.getStatus());
//			complainHistoryDTO.setDescription(c.getDescription());
//			complainHistoryDTO.setSeverity(complaintType.getSeverity());
//
//			dto.add(complainHistoryDTO);
//
//		}
//
//		return dto;
//	}

	@Override
	@Cacheable("complainTypesByQuery")
	public List<ComplaintTypeDTO> getComplaintTypes() {

		List<Department> departments = departmentRepo.findAll();

		List<ComplaintTypeDTO> dto = new ArrayList<ComplaintTypeDTO>();

		for (Department dept : departments) {
			List<ComplaintType> complaintTypes = complainTypeRepo.findByDeptId(dept.getDeptId());
			List<ComplaintType> complaintTypeDTOs = complaintTypes.stream().map(ct -> {
				ComplaintType complaintType = new ComplaintType();
				complaintType.setComplainType(ct.getComplainType());
				complaintType.setCtid(ct.getCtid());
				complaintType.setSeverity(ct.getSeverity());
				return complaintType;
			}).collect(Collectors.toList());

			ComplaintTypeDTO complaintTypeDTO = new ComplaintTypeDTO();
			complaintTypeDTO.setComplaintTypes(complaintTypeDTOs);
			complaintTypeDTO.setDeptId(dept.getDeptId());
			complaintTypeDTO.setDeptName(dept.getDeptName());

			dto.add(complaintTypeDTO);

		}
		return dto;
	}

}
