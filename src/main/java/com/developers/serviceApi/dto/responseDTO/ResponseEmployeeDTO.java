package com.developers.serviceApi.dto.responseDTO;

import com.developers.serviceApi.dto.UserTypeDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseEmployeeDTO {
    private String employeeId;
    private String employeeName;
    private String employeeAddress;
    private String employeeEmail;
    private String employeeMobile;
    private boolean employmentState;
    private boolean activeState;
    private String branchName;
    private String userTypeName;

}
