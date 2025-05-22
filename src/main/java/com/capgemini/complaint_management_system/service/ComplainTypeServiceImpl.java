package com.capgemini.complaint_management_system.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.capgemini.complaint_management_system.entities.ComplaintType;
import com.capgemini.complaint_management_system.exception.ComplaintTypeNotFoundException;
import com.capgemini.complaint_management_system.repository.ComplainTypeRepo;

@Service
public class ComplainTypeServiceImpl implements ComplainTypeService {

	ComplainTypeRepo complainTypeRepo;

	@Autowired
	public ComplainTypeServiceImpl(ComplainTypeRepo complainTypeRepo) {
		super();
		this.complainTypeRepo = complainTypeRepo;
	}

	@Override
	@CacheEvict(value = "complainTypes" ,allEntries = true)
	public ComplaintType addComplaintType(ComplaintType complaintType) {
		return complainTypeRepo.save(complaintType);
	}

	@Override
	@CacheEvict(value = "complainTypes" ,allEntries = true)
	public ComplaintType updateComplaintType(Integer id, ComplaintType complaintType) {
		ComplaintType existing = complainTypeRepo.findById(id)
				.orElseThrow(() -> new ComplaintTypeNotFoundException("Complain Type doesn't exist with Id" + id));
		existing.setComplainType(complaintType.getComplainType());
		existing.setDeptId(complaintType.getDeptId());
		existing.setSeverity(complaintType.getSeverity());
		return complainTypeRepo.save(existing);
	}

	@Override
	public ComplaintType findComplaintType(Integer id) {
		return complainTypeRepo.findById(id)
				.orElseThrow(() -> new ComplaintTypeNotFoundException("Complain Type doesn't exist with ID:" + id));

	}

	@Override
	@CacheEvict(value = "complainTypes" ,allEntries = true)
	public boolean deleteComplainType(Integer id) {
		complainTypeRepo.findById(id)
				.orElseThrow(() -> new ComplaintTypeNotFoundException("Complain Type not found with id: " + id));
		complainTypeRepo.deleteById(id);
		return true;
	}

	@Override
	@Cacheable(value = "complainTypes")
	public List<ComplaintType> findAllComplainTypes() {
		return complainTypeRepo.findAll();
	}

	@Override
	@Cacheable(value = "complainTypesByDeptId")
	public List<ComplaintType> findComplaintTypesByDeptId(Integer deptId) {
		List<ComplaintType> complainTypes = complainTypeRepo.findByDeptId(deptId);
		return complainTypes;
	}

}
