package com.developers.serviceApi.service;

import com.developers.serviceApi.dto.requestDTO.RequestEmployeeDTO;
import com.developers.serviceApi.dto.requestDTO.RequestSalaryDTO;
import com.developers.serviceApi.dto.responseDTO.CommonResponseDTO;
import com.developers.serviceApi.dto.responseDTO.ResponseSalaryDTO;

import java.util.List;

public interface SalaryService {
    CommonResponseDTO create(RequestSalaryDTO dto, String userTypeId);

    List<ResponseSalaryDTO> getAll(String month);
}
