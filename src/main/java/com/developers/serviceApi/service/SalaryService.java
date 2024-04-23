package com.developers.serviceApi.service;

import com.developers.serviceApi.dto.requestDTO.RequestSalaryDTO;
import com.developers.serviceApi.dto.responseDTO.CommonResponseDTO;
import com.developers.serviceApi.dto.responseDTO.ResponseSalaryDTO;

import java.util.List;

public interface SalaryService {
    /**
     * This method provides create salaries function
     *
     * @param dto
     * @param userTypeId
     * @return CommonResponseDTO
     */
    CommonResponseDTO create(RequestSalaryDTO dto, String userTypeId);

    /**
     * This method provides get all salary function
     *
     * @param month
     * @return List<ResponseSalaryDTO>
     */
    List<ResponseSalaryDTO> getAll(String month);
}
