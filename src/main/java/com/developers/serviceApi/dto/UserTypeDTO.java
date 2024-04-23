package com.developers.serviceApi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserTypeDTO {
    private String userTypeId;
    private String userTypeName;
    private boolean activeState;

}
