package com.capgemini.complaint_management_system.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ComplaintsOverTimeDTO {

    private LocalDate date;
    private Long count;

}
