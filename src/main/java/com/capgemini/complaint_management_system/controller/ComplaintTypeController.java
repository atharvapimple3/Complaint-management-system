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

import com.capgemini.complaint_management_system.DTO.ComplaintTypeDTO;
import com.capgemini.complaint_management_system.entities.ComplaintType;
import com.capgemini.complaint_management_system.service.ComplainTypeService;
import com.capgemini.complaint_management_system.service.DTOService;

@RestController
@RequestMapping("/api/complaintTypes")
@CrossOrigin(origins = "http://127.0.0.1:5500")
public class ComplaintTypeController {

	ComplainTypeService complainTypeService;
	DTOService dtoService;

	@Autowired
	public ComplaintTypeController(ComplainTypeService complainTypeService, DTOService dtoService) {
		super();
		this.complainTypeService = complainTypeService;
		this.dtoService = dtoService;
	}

	@GetMapping
	public ResponseEntity<List<ComplaintTypeDTO>> getAllTypes() {
		return ResponseEntity.status(HttpStatus.OK).body(dtoService.getComplaintTypes());
	}

	@GetMapping("/{id}")
	public ResponseEntity<ComplaintType> getComplaintType(@PathVariable("id") Integer id) {
		return ResponseEntity.status(HttpStatus.OK).body(complainTypeService.findComplaintType(id));
	}

	@GetMapping("/departments/{id}")
	public ResponseEntity<List<ComplaintType>> getComplaintTypeByDepartment(@PathVariable("id") Integer id) {
		return ResponseEntity.status(HttpStatus.OK).body(complainTypeService.findComplaintTypesByDeptId(id));
	}

	@PostMapping()
	public ResponseEntity<ComplaintType> addComplaintType(@RequestBody ComplaintType complaintType) {
		return ResponseEntity.status(HttpStatus.CREATED).body(complainTypeService.addComplaintType(complaintType));
	}

	@PutMapping("/{id}")
	public ResponseEntity<ComplaintType> updateComplainType(@PathVariable("id") Integer id,
			@RequestBody ComplaintType complaintType) {
		return ResponseEntity.status(HttpStatus.ACCEPTED)
				.body(complainTypeService.updateComplaintType(id, complaintType));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<ComplaintType> deleteComplaintType(@PathVariable("id") Integer id) {
		complainTypeService.deleteComplainType(id);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();

	}

}
