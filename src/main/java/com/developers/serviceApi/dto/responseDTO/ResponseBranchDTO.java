package com.developers.serviceApi.dto.responseDTO;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseBranchDTO {
    private String branchId;
    private String branchName;
    private boolean activeState;
}
