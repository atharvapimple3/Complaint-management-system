package com.capgemini.complaint_management_system.controller;

import com.capgemini.complaint_management_system.DTO.AllComplaintsDTO;
import com.capgemini.complaint_management_system.service.ComplaintService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/query")
@CrossOrigin(origins = "*")
public class QueryController {

	ComplaintService complaintService;

	public QueryController(ComplaintService complaintService) {
		this.complaintService = complaintService;
	}

	@GetMapping("/between-dates")
	public ResponseEntity<List<AllComplaintsDTO>> getComplaintsFiledBetween(@RequestParam("start") String start,
			@RequestParam("end") String end) {
		try {
			LocalDate startDate = LocalDate.parse(start);
			LocalDate endDate = LocalDate.parse(end);
			return ResponseEntity.status(HttpStatus.OK)
					.body(complaintService.getComplintsFiledBetween(startDate, endDate));
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
	}

}
