package com.capgemini.complaint_management_system.service;

import java.time.LocalDate;
import java.util.List;
import java.util.TreeMap;
import java.util.stream.Collectors;

import com.capgemini.complaint_management_system.DTO.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.capgemini.complaint_management_system.entities.Complaint;
import com.capgemini.complaint_management_system.exception.ComplaintNotFoundException;
import com.capgemini.complaint_management_system.repository.ComplaintRepo;

@Service
public class ComplaintServiceImpl implements ComplaintService {

	ComplaintRepo complaintRepo;

	@Autowired
	public ComplaintServiceImpl(ComplaintRepo complaintRepo) {
		super();
		this.complaintRepo = complaintRepo;
	}

	@Override
	@CacheEvict(value = "complaints" , allEntries = true)
	public Complaint addComplaint(Complaint complaint) {
		return complaintRepo.save(complaint);
	}

	@Override
	@CacheEvict(value = "complaints" , allEntries = true)
	public Complaint updateComplaint(Integer id, Complaint complaint) {
		Complaint existing = complaintRepo.findById(id)
				.orElseThrow(() -> new ComplaintNotFoundException("Complaint not found with ID: " + id));
		existing.setUserId(complaint.getUserId());
		existing.setDeptId(complaint.getDeptId());
		existing.setDateFiled(complaint.getDateFiled());
		existing.setDescription(complaint.getDescription());
		existing.setCtid(complaint.getCtid());
		existing.setStatus(complaint.getStatus());
		return complaintRepo.save(existing);
	}

	@Override
	public Complaint findComplaint(Integer id) {
		return complaintRepo.findById(id)
				.orElseThrow(() -> new ComplaintNotFoundException("Complaint not found with ID " + id));
	}

	@Override
	@CacheEvict(value = "complaints",allEntries = true)
	public boolean deleteComplaint(Integer id) {
		complaintRepo.findById(id)
				.orElseThrow(() -> new ComplaintNotFoundException("Complaint not found woith ID: " + id));
		complaintRepo.deleteById(id);
		return true;
	}

	@Override
	@Cacheable(value = "complaints")
	public List<Complaint> findAllComplaints() {
		return complaintRepo.findAll();
	}

	@Override
	@Cacheable(value = "complainHistory")
	public List<ComplainHistoryDTO> findComplaintsByQuery(Integer userId) {
		return complaintRepo.findByIdByQuery(userId);
	}

	@Override
	@Cacheable(value = "approveComplaints")
	public List<ApproveComplaintsDTO> getComplaintsToApprove() {
		return complaintRepo.getComplaintsToApprove();
	}
	@Override
	@Cacheable("allComplaints")
	public List<AllComplaintsDTO> getAllComplains() {
		return complaintRepo.getAllComplaints();
	}
	@Override
	@Cacheable("updateComplaints")
	public List<UpdateComplaintStatusDTO> getComplaintsToUpdateStatus() {
		return complaintRepo.getComplaintsToUpdate();
	}
	@Override
	@Cacheable("editComplaintsById")
	public List<EditComplaintsByIdDTO> getComplaintsToEdit(Integer complainId) {
		return complaintRepo.getComplaintsByIdToEdit(complainId);
	}

	@Override
	public List<AllComplaintsDTO> getComplintsFiledBetween(LocalDate start, LocalDate end) {
		return complaintRepo.findByDataFiledBetween(start,end);
	}


}
