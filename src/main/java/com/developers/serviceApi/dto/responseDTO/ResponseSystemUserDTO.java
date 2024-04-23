package com.developers.serviceApi.dto.responseDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseSystemUserDTO {
    private String systemUserId;
    private String userName;
    private String systemUserEmail;
    // Omit password for security reasons (should never be exposed in responses)
}
