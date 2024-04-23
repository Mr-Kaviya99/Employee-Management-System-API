package com.developers.serviceApi.util.mapper;

import com.developers.serviceApi.dto.SalaryDTO;
import com.developers.serviceApi.entity.Salary;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SalaryMapper {

    Salary toSalary(SalaryDTO salaryDTO);

    SalaryDTO toSalaryDto(Salary salary);
}
