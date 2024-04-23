package com.developers.serviceApi.service;

import com.developers.serviceApi.dto.requestDTO.RequestEmployeeDTO;
import com.developers.serviceApi.dto.responseDTO.CommonResponseDTO;
import com.developers.serviceApi.dto.responseDTO.ResponseEmployeeDTO;

import java.util.List;

public interface EmployeeService {

    /**
     * This method provides create employee function
     *
     * @param dto
     * @param branchId
     * @param userTypeId
     * @return CommonResponseDTO
     */
    CommonResponseDTO create(RequestEmployeeDTO dto, String branchId, String userTypeId);

    /**
     * This method provides get all employee function
     *
     * @param employeeAvailability
     * @param employmentState
     * @param branchId
     * @param userTypeId
     * @return List<ResponseEmployeeDTO>
     */
    List<ResponseEmployeeDTO> getAll(String employeeAvailability, String employmentState, String branchId, String userTypeId);

    /**
     * This method provides get employee by id function
     *
     * @param employeeId
     * @return ResponseEmployeeDTO
     */
    ResponseEmployeeDTO getById(String employeeId);

    /**
     * This method provides change employee active status function
     *
     * @param state
     * @param employeeId
     * @return CommonResponseDTO
     */
    CommonResponseDTO changeState(boolean state, String employeeId);

    /**
     * This method provides change employee employment state function
     *
     * @param employmentState
     * @param employeeId
     * @return CommonResponseDTO
     */
    CommonResponseDTO changeEmploymentState(boolean employmentState, String employeeId);

    /**
     * This method provides get employee count function
     *
     * @return Object
     */
    Object getEmployeeCount();
}
