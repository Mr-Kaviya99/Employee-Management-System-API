package com.developers.serviceApi.service;

import com.developers.serviceApi.dto.requestDTO.RequestEmployeeDTO;
import com.developers.serviceApi.dto.requestDTO.RequestUserTypeDTO;
import com.developers.serviceApi.dto.responseDTO.CommonResponseDTO;
import com.developers.serviceApi.dto.responseDTO.ResponseEmployeeDTO;

import java.util.List;

public interface EmployeeService {

    CommonResponseDTO create(RequestEmployeeDTO dto, String branchId, String userTypeId);



    List<ResponseEmployeeDTO> getAll(String employeeAvailability, String employmentState,String branchId,String userTypeId);


    ResponseEmployeeDTO getById(String employeeId);

    CommonResponseDTO changeState(boolean state,String employeeId);

    CommonResponseDTO changeEmploymentState(boolean employmentState, String employeeId);

     Object getEmployeeCount();
}
