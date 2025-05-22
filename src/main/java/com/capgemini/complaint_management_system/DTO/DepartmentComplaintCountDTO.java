package com.capgemini.complaint_management_system.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DepartmentComplaintCountDTO {

    private String deptName;
    private Long complaintCount;
}
