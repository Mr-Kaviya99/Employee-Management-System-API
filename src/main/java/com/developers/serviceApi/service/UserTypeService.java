package com.developers.serviceApi.service;

import com.developers.serviceApi.dto.requestDTO.RequestUserTypeDTO;
import com.developers.serviceApi.dto.responseDTO.CommonResponseDTO;
import com.developers.serviceApi.dto.responseDTO.ResponseUserTypeDTO;

import java.util.List;

public interface UserTypeService {
    /**
     * This method provides create user type function
     *
     * @param dto
     * @return CommonResponseDTO
     */
    CommonResponseDTO create(RequestUserTypeDTO dto);

    /**
     * This method provides get all user type function
     *
     * @return List<ResponseUserTypeDTO>s
     */
    List<ResponseUserTypeDTO> getAll();

    /**
     * This method provides delete user type function
     *
     * @param userTypeId
     * @return
     */
    CommonResponseDTO delete(String userTypeId);

    /**
     * This method provides get user type count function
     *
     * @return Object
     */
    Object getUserTypeCount();
}
