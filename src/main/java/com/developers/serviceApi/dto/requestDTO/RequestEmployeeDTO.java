package com.developers.serviceApi.dto.requestDTO;

import com.developers.serviceApi.dto.UserTypeDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RequestEmployeeDTO {

    private String employeeName;
    private String employeeAddress;
    private String employeeEmail;
    private String employeeMobile;


}
