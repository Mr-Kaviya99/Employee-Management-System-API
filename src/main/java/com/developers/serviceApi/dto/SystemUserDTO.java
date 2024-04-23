package com.developers.serviceApi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SystemUserDTO {
    private String systemUserId;
    private String userName;
    private String systemUserEmail;
    private String password;

}
