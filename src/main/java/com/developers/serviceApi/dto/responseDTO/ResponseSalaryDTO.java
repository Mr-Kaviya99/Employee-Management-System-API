package com.developers.serviceApi.dto.responseDTO;

import com.developers.serviceApi.dto.EmployeeDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseSalaryDTO {
    private String salaryId;
    private String employeeId;
    private String  employeeName;
    private String month;
    private Double amount;
    private String userType;
    private String branchName;
    private boolean paidStatus;

}
