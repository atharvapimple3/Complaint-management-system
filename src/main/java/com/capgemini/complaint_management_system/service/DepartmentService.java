package com.capgemini.complaint_management_system.service;

import java.util.List;

import com.capgemini.complaint_management_system.DTO.DepartmentComplaintCountDTO;
import com.capgemini.complaint_management_system.entities.Department;

public interface DepartmentService {

	Department addDepartment(Department department);

	Department updateDepartment(Integer id, Department department);

	Department findDepartment(Integer id);

	boolean deleteDepartment(Integer id);

	List<Department> findAllDepartments();

	List<DepartmentComplaintCountDTO> countByDepartment();

}
