package com.developers.serviceApi.service.impl;

import com.developers.serviceApi.dto.SalaryDTO;
import com.developers.serviceApi.dto.requestDTO.RequestSalaryDTO;
import com.developers.serviceApi.dto.responseDTO.CommonResponseDTO;
import com.developers.serviceApi.dto.responseDTO.ResponseSalaryDTO;
import com.developers.serviceApi.entity.Employee;
import com.developers.serviceApi.entity.Salary;
import com.developers.serviceApi.entity.UserType;
import com.developers.serviceApi.exception.EntryDuplicateException;
import com.developers.serviceApi.exception.EntryNotFoundException;
import com.developers.serviceApi.repo.EmployeeRepo;
import com.developers.serviceApi.repo.SalaryRepo;
import com.developers.serviceApi.repo.UserTypeRepo;
import com.developers.serviceApi.service.SalaryService;
import com.developers.serviceApi.util.Generator;
import com.developers.serviceApi.util.mapper.EmployeeMapper;
import com.developers.serviceApi.util.mapper.SalaryMapper;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class SalaryServiceImpl implements SalaryService {
    private final Generator generator;
    private final SalaryRepo salaryRepo;
    private final SalaryMapper salaryMapper;
    private final EmployeeRepo employeeRepo;
    private final EmployeeMapper employeeMapper;
    private final UserTypeRepo userTypeRepo;

    public SalaryServiceImpl(Generator generator, SalaryRepo salaryRepo, SalaryMapper salaryMapper,
                             EmployeeRepo employeeRepo, EmployeeMapper employeeMapper,
                             UserTypeRepo userTypeRepo) {
        this.generator = generator;
        this.salaryRepo = salaryRepo;
        this.salaryMapper = salaryMapper;
        this.employeeRepo = employeeRepo;
        this.employeeMapper = employeeMapper;
        this.userTypeRepo = userTypeRepo;
    }

    @Override
    @Transactional
    public CommonResponseDTO create(RequestSalaryDTO dto, String userTypeId) {

        Optional<UserType> userType= userTypeRepo.findById(userTypeId);
        if(userType.isEmpty()){
            throw new EntryNotFoundException("UserType Not Found");
        }

        List<Employee> employees = employeeRepo.findByUserTypeId(userTypeId);
        for(Employee u : employees){

            List<Salary> salary = salaryRepo.findByEmployeeId(u.getEmployeeId());
            if(!salary.isEmpty()){
                for (Salary s: salary){
                    if(s.getMonth().equals(dto.getMonth())){
                        throw new EntryDuplicateException("Already Added For This Month");
                    }
                }
            }
            //--------------------------------------------------------------------------------------------------------------
            String prefix="SAPI-S-";
            String propertyId = generator.generateNewId(prefix,salaryRepo.findLastId(prefix,prefix.length()+1));
            //---------------------------------------------------------------------

            SalaryDTO salaryDTO= new SalaryDTO(
                    propertyId,
                    dto.getMonth(),
                    dto.getAmount(),
                    true,
                    employeeMapper.toEmployeeDTO(u)
            );
            salaryRepo.save(salaryMapper.toSalary(salaryDTO));
        }

        return new CommonResponseDTO(200,"Saved !!",userTypeId,new ArrayList<>());

    }

    @Override
    public List<ResponseSalaryDTO> getAll(String month) {
        List<Salary> list = salaryRepo.findBySearchText(month);
        ArrayList<ResponseSalaryDTO> arrayList = new ArrayList<>();

        for(Salary u :list){
            arrayList.add(new ResponseSalaryDTO(
                    u.getSalaryId(),
                    u.getEmployee().getEmployeeId(),
                    u.getEmployee().getEmployeeName(),
                    u.getMonth(),
                    u.getAmount(),
                    u.getEmployee().getUserType().getUserTypeName(),
                    u.getEmployee().getBranch().getBranchName(),
                    u.isPaidStatus()




            ));
        }
        return arrayList;
    }

}