package com.capgemini.complaint_management_system.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.capgemini.complaint_management_system.DTO.AllComplaintsDTO;
import com.capgemini.complaint_management_system.DTO.ApproveComplaintsDTO;
import com.capgemini.complaint_management_system.DTO.ComplainHistoryDTO;
import com.capgemini.complaint_management_system.DTO.EditComplaintsByIdDTO;
import com.capgemini.complaint_management_system.DTO.UpdateComplaintStatusDTO;
import com.capgemini.complaint_management_system.entities.Complaint;
import com.capgemini.complaint_management_system.service.ComplaintService;
import com.capgemini.complaint_management_system.service.DTOService;

@RestController
@RequestMapping("/api/complaints")
public class ComplaintController {

	ComplaintService complaintService;
	DTOService dtoService;

	@Autowired
	public ComplaintController(ComplaintService complaintService, DTOService dtoService) {
		this.dtoService = dtoService;
		this.complaintService = complaintService;
	}

	@GetMapping
	public ResponseEntity<List<AllComplaintsDTO>> getAllComplaints() {
		return ResponseEntity.status(HttpStatus.OK).body(complaintService.getAllComplains());
	}
	@GetMapping("/update-status")
	public ResponseEntity<List<UpdateComplaintStatusDTO>> getAllComplaintsToUpdateStatus() {
		return ResponseEntity.status(HttpStatus.OK).body(complaintService.getComplaintsToUpdateStatus());
	}

	@GetMapping("/{id}")
	public ResponseEntity<List<EditComplaintsByIdDTO>> getComplaint(@PathVariable("id") Integer id) {
		return ResponseEntity.status(HttpStatus.OK).body(complaintService.getComplaintsToEdit(id));
	}

//	@GetMapping("/history/{userId}")
//	public ResponseEntity<List<ComplainHistoryDTO>> getComplainHistory(@PathVariable("userId") Integer userId) {
//		return ResponseEntity.status(HttpStatus.OK).body(dtoService.getAllComplains(userId));
//	}

	@GetMapping("/history/{userId}")
	public ResponseEntity<List<ComplainHistoryDTO>> getComplainHistory(@PathVariable("userId") Integer userId) {
		return ResponseEntity.status(HttpStatus.OK).body(complaintService.findComplaintsByQuery(userId));
	}

	@GetMapping("/approve")
	public ResponseEntity<List<ApproveComplaintsDTO>> getComplainsToApprove() {
		return ResponseEntity.status(HttpStatus.OK).body(complaintService.getComplaintsToApprove());
	}

	@PostMapping
	public ResponseEntity<Complaint> addComplaint(@RequestBody Complaint complaint) {
		return ResponseEntity.status(HttpStatus.CREATED).body(complaintService.addComplaint(complaint));
	}

	@PutMapping("/{id}")
	public ResponseEntity<Complaint> updateComplaint(@PathVariable("id") Integer id, @RequestBody Complaint complaint) {
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(complaintService.updateComplaint(id, complaint));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Complaint> deleteComplaint(@PathVariable("id") Integer id) {
		complaintService.deleteComplaint(id);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}

}
