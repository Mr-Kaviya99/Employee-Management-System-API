package com.developers.serviceApi.service;

import com.developers.serviceApi.dto.requestDTO.RequestBranchDTO;
import com.developers.serviceApi.dto.responseDTO.CommonResponseDTO;
import com.developers.serviceApi.dto.responseDTO.ResponseBranchDTO;

import java.util.List;

public interface BranchService {
    /**
     * This method provides create branch function
     *
     * @param dto
     * @return CommonResponseDTO
     */
    CommonResponseDTO create(RequestBranchDTO dto);

    /**
     * This method provides all data of branch
     *
     * @return List<ResponseBranchDTO>
     */
    List<ResponseBranchDTO> getAll();

    /**
     * This method provides delete branch function
     *
     * @param branchId
     * @return CommonResponseDTO
     */
    CommonResponseDTO delete(String branchId);

    /**
     * This method provides get branch count function
     *
     * @return Object
     */
    Object getBranchCount();
}
