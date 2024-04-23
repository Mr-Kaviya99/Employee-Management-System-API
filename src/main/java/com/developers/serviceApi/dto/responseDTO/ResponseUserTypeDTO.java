package com.developers.serviceApi.dto.responseDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseUserTypeDTO {
    private String userTypeId;
    private String userTypeName;
    private boolean activeState;

}

