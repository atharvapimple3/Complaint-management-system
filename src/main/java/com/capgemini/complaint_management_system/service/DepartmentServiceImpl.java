package com.capgemini.complaint_management_system.service;

import java.util.List;

import com.capgemini.complaint_management_system.DTO.DepartmentComplaintCountDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.capgemini.complaint_management_system.entities.Department;
import com.capgemini.complaint_management_system.exception.DepartmentNotFoundException;
import com.capgemini.complaint_management_system.repository.DepartmentRepo;

@Service
public class DepartmentServiceImpl implements DepartmentService {

	DepartmentRepo departmentRepo;

	@Autowired
	public DepartmentServiceImpl(DepartmentRepo departmentRepo) {
		super();
		this.departmentRepo = departmentRepo;
	}

	@Override
	@CacheEvict(value = "departments" ,allEntries = true)
	public Department addDepartment(Department department) {
		return departmentRepo.save(department);
	}

	@Override
	@CacheEvict(value = "departments",allEntries = true)
	public Department updateDepartment(Integer id, Department department) {
		Department dept = departmentRepo.findById(id)
				.orElseThrow(() -> new DepartmentNotFoundException("Department doesn't exist with ID:" + id));
		dept.setDeptName(department.getDeptName());
		dept.setContact(department.getContact());
		return departmentRepo.save(dept);
	}

	@Override
	public Department findDepartment(Integer id) {
		return departmentRepo.findById(id)
				.orElseThrow(() -> new DepartmentNotFoundException("Department doesn't exists with ID:" + id));
	}

	@Override
	@CacheEvict(value = "departments" , allEntries = true)
	public boolean deleteDepartment(Integer id) {
		departmentRepo.findById(id)
				.orElseThrow(() -> new DepartmentNotFoundException("Department doesn't exists with ID: " + id));
		departmentRepo.deleteById(id);
		return true;
	}

	@Override
	@Cacheable(value = "departments")
	public List<Department> findAllDepartments() {
		return departmentRepo.findAll();
	}

	@Override
	public List<DepartmentComplaintCountDTO> countByDepartment() {
		return departmentRepo.countByDepartment();
	}

}
