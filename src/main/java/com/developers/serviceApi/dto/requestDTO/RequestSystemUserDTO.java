package com.developers.serviceApi.dto.requestDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RequestSystemUserDTO {
    private String systemUserId;
    private String userName;
    private String systemUserEmail;
    private String password; // Include password for creation requests (handle securely)
}
