package com.developers.serviceApi.dto;

import com.developers.serviceApi.entity.Branch;
import com.developers.serviceApi.entity.UserType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeDTO {
    private String employeeId;
    private String employeeName;
    private String employeeAddress;
    private String employeeEmail;
    private String employeeMobile;
    private boolean employmentState;
    private boolean activeState;
    private UserTypeDTO userType;
    private BranchDTO branch;

}
