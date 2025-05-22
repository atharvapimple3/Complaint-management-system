package com.capgemini.complaint_management_system.repository;

import com.capgemini.complaint_management_system.DTO.DepartmentComplaintCountDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.capgemini.complaint_management_system.entities.Department;

import java.util.List;

@Repository
public interface DepartmentRepo extends JpaRepository<Department, Integer> {

    @Query(
            "select new com.capgemini.complaint_management_system.DTO.DepartmentComplaintCountDTO(d.deptName, COUNT(c)) " +
                    "from Complaint c join Department d on c.deptId = d.deptId group by d.deptName")
    List<DepartmentComplaintCountDTO> countByDepartment();

}
