package com.capgemini.complaint_management_system.controller;

import com.capgemini.complaint_management_system.DTO.DepartmentComplaintCountDTO;
import com.capgemini.complaint_management_system.repository.DepartmentRepo;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/dashboard")
@CrossOrigin(origins = "*")
public class DashboardController {

    DepartmentRepo departmentRepo;

    public DashboardController(DepartmentRepo departmentRepo){
        this.departmentRepo = departmentRepo;
    }

    @GetMapping("/complaints-by-department")
    public ResponseEntity<List<DepartmentComplaintCountDTO>> countByDepartment(){
        return ResponseEntity.status(HttpStatus.OK).body(departmentRepo.countByDepartment());
    }

}
