package com.developers.serviceApi.dto.requestDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RequestSalaryDTO {
    private String month;
    private Double amount;

}
