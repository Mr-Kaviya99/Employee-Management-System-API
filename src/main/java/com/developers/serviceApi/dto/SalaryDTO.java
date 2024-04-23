package com.developers.serviceApi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SalaryDTO {
    private String salaryId;
    private String month;
    private Double amount;
    private boolean paidStatus;
    private EmployeeDTO employee; // Assuming EmployeeDTO exists for Employee entity
}
