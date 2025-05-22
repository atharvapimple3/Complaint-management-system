package com.capgemini.complaint_management_system.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.capgemini.complaint_management_system.entities.ComplaintType;

@Repository
public interface ComplainTypeRepo extends JpaRepository<ComplaintType, Integer> {
	
	List<ComplaintType> findByDeptId(Integer deptId);

}
