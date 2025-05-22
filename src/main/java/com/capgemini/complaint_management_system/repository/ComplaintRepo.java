package com.capgemini.complaint_management_system.repository;

import java.time.LocalDate;
import java.util.List;

import com.capgemini.complaint_management_system.DTO.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.capgemini.complaint_management_system.entities.Complaint;

@Repository
public interface ComplaintRepo extends JpaRepository<Complaint, Integer> {

    List<Complaint> findByUserId(Integer UserId);

    @Query("select new com.capgemini.complaint_management_system.DTO.ComplainHistoryDTO"
            + "(d.deptName,ct.complainType,c.description,c.status,ct.severity,c.dateFiled)"
            + "from Complaint c "
            + "join Department d on c.deptId = d.deptId "
            + "join ComplaintType ct on ct.ctid = c.ctid "
            + "where c.userId = ?1")
    List<ComplainHistoryDTO> findByIdByQuery(Integer userId);


    @Query("select new com.capgemini.complaint_management_system.DTO.ApproveComplaintsDTO"
            + "(c.complainId,u.name,u.userId,d.deptName,ct.complainType,ct.severity,c.description,c.dateFiled)"
            + "from Complaint c "
            + "join Department d on c.deptId = d.deptId "
            + "join User u on u.userId = c.userId "
            + "join ComplaintType ct on ct.ctid = c.ctid "
            + "where c.status = \"pending\"")
    List<ApproveComplaintsDTO> getComplaintsToApprove();


    @Query("select new com.capgemini.complaint_management_system.DTO.AllComplaintsDTO "
            + "(u.userId,u.name,d.deptName,ct.complainType,c.status,c.complainId,c.description,c.dateFiled)"
            + "from Complaint c "
            + "join Department d on c.deptId = d.deptId "
            + "join User u on c.userId = u.userId "
            + "join ComplaintType ct on ct.ctid = c.ctid")
    List<AllComplaintsDTO> getAllComplaints();

    @Query("select new com.capgemini.complaint_management_system.DTO.UpdateComplaintStatusDTO "
            + "(c.complainId,u.userId,u.name,d.deptName,ct.complainType,ct.severity,c.description,c.dateFiled,c.status)"
            + "from Complaint c "
            + "join Department d on c.deptId = d.deptId "
            + "join User u on c.userId = u.userId "
            + "join ComplaintType ct on ct.ctid = c.ctid "
            + "where c.status=\"pending\" or c.status=\"in-progress\" or c.status=\"open\"")
    List<UpdateComplaintStatusDTO> getComplaintsToUpdate();

    @Query("select new com.capgemini.complaint_management_system.DTO.EditComplaintsByIdDTO"
            + "(u.userId,u.name,d.deptName,ct.complainType,c.status,c.complainId,c.description,ct.severity)"
            + "from Complaint c "
            + "join Department d on c.deptId = d.deptId "
            + "join ComplaintType ct on ct.ctid = c.ctid "
            + "join User u on u.userId = c.userId "
            + "where c.complainId = ?1")
    List<EditComplaintsByIdDTO> getComplaintsByIdToEdit(Integer id);


    @Query("select new com.capgemini.complaint_management_system.DTO.AllComplaintsDTO " +
            " (u.userId,u.name,d.deptName,ct.complainType,c.status,c.complainId,c.description,c.dateFiled) " +
            " from Complaint c " +
            " join Department d on c.deptId = d.deptId " +
            " join User u on c.userId = u.userId " +
            " join ComplaintType ct on c.ctid = ct.ctid" +
            " where c.dateFiled between :start and :end")
    List<AllComplaintsDTO> findByDataFiledBetween(@Param("start") LocalDate start, @Param("end") LocalDate end);

}
