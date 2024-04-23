package com.developers.serviceApi.service;

import com.developers.serviceApi.dto.requestDTO.RequestUserTypeDTO;
import com.developers.serviceApi.dto.responseDTO.CommonResponseDTO;
import com.developers.serviceApi.dto.responseDTO.ResponseUserTypeDTO;

import java.util.List;

public interface UserTypeService {
    CommonResponseDTO create(RequestUserTypeDTO dto);

    List<ResponseUserTypeDTO> getAll();

    CommonResponseDTO delete(String userTypeId);

    Object getUserTypeCount();
}
