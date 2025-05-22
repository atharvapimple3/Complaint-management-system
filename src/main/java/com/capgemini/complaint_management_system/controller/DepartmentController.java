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

import com.capgemini.complaint_management_system.entities.Department;
import com.capgemini.complaint_management_system.service.DepartmentService;

@RestController
@RequestMapping("/api/departments")
@CrossOrigin(origins = "http://127.0.0.1:5500")
public class DepartmentController {

	DepartmentService departmentService;

	@Autowired
	public DepartmentController(DepartmentService departmentService) {
		super();
		this.departmentService = departmentService;
	}

	@GetMapping
	public ResponseEntity<List<Department>> getAllDepts() {
		return ResponseEntity.status(HttpStatus.OK).body(departmentService.findAllDepartments());
	}

	@GetMapping("/{id}")
	public ResponseEntity<Department> getDepartment(@PathVariable("id") Integer id) {
		return ResponseEntity.status(HttpStatus.OK).body(departmentService.findDepartment(id));
	}

	@PostMapping()
	public ResponseEntity<Department> addDepartment(@RequestBody Department department) {
		return ResponseEntity.status(HttpStatus.CREATED).body(departmentService.addDepartment(department));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Department> deleteDept(@PathVariable("id") Integer id) {
		departmentService.deleteDepartment(id);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}

	@PutMapping("/{id}")
	public ResponseEntity<Department> updateDept(@PathVariable("id") Integer id, @RequestBody Department department) {
		return ResponseEntity.status(HttpStatus.OK).body(departmentService.updateDepartment(id, department));
	}

}
