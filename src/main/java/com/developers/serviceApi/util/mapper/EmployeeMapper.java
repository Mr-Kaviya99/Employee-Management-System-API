package com.developers.serviceApi.util.mapper;

import com.developers.serviceApi.dto.EmployeeDTO;
import com.developers.serviceApi.entity.Employee;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface EmployeeMapper {

    Employee toEmployee(EmployeeDTO employeeDTO);
    EmployeeDTO toEmployeeDTO(Employee employee);
}
