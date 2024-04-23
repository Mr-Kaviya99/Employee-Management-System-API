package com.developers.serviceApi.service.impl;

import com.developers.serviceApi.dto.EmployeeDTO;
import com.developers.serviceApi.dto.requestDTO.RequestEmployeeDTO;
import com.developers.serviceApi.dto.responseDTO.CommonResponseDTO;
import com.developers.serviceApi.dto.responseDTO.CountDTO;
import com.developers.serviceApi.dto.responseDTO.ResponseEmployeeDTO;
import com.developers.serviceApi.entity.Branch;
import com.developers.serviceApi.entity.Employee;
import com.developers.serviceApi.entity.UserType;
import com.developers.serviceApi.exception.EntryNotFoundException;
import com.developers.serviceApi.repo.BranchRepo;
import com.developers.serviceApi.repo.EmployeeRepo;
import com.developers.serviceApi.repo.UserTypeRepo;
import com.developers.serviceApi.service.EmployeeService;
import com.developers.serviceApi.util.Generator;
import com.developers.serviceApi.util.mapper.BranchMapper;
import com.developers.serviceApi.util.mapper.EmployeeMapper;
import com.developers.serviceApi.util.mapper.UserTypeMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    private final Generator generator;
    private final EmployeeRepo employeeRepo;
    private final EmployeeMapper employeeMapper;
    private final BranchRepo branchRepo;
    private final BranchMapper branchMapper;
    private final UserTypeRepo userTypeRepo;
    private final UserTypeMapper userTypeMapper;

    public EmployeeServiceImpl(Generator generator, EmployeeRepo employeeRepo, EmployeeMapper employeeMapper, BranchRepo branchRepo, BranchMapper branchMapper, UserTypeRepo userTypeRepo, UserTypeMapper userTypeMapper) {
        this.generator = generator;
        this.employeeRepo = employeeRepo;
        this.employeeMapper = employeeMapper;
        this.branchRepo = branchRepo;
        this.branchMapper = branchMapper;
        this.userTypeRepo = userTypeRepo;
        this.userTypeMapper = userTypeMapper;
    }

    @Override
    public CommonResponseDTO create(RequestEmployeeDTO dto, String branchId, String userTypeId) {
        Optional<Branch> branch = branchRepo.findById(branchId);
        if (branch.isEmpty()) {
            throw new EntryNotFoundException("Branch Not Found");
        }
        Optional<UserType> userType = userTypeRepo.findById(userTypeId);
        if (userType.isEmpty()) {
            throw new EntryNotFoundException("User Type Not Found");
        }

        //--------------------------------------------------------------------------------------------------------------
        String prefix = "SAPI-E-";
        String propertyId = generator.generateNewId(prefix, employeeRepo.findLastId(prefix, prefix.length() + 1));
        //-------------------------------------------------------------------------------------------------------------

        EmployeeDTO employeeDTO = new EmployeeDTO(
                propertyId,
                dto.getEmployeeName(),
                dto.getEmployeeAddress(),
                dto.getEmployeeEmail(),
                dto.getEmployeeMobile(),
                true,
                true,
                userTypeMapper.toUserTypeDto(userType.get()),
                branchMapper.toBranchDTO(branch.get())

        );

        employeeRepo.save(employeeMapper.toEmployee(employeeDTO));

        return new CommonResponseDTO(201, " saved!", true, new ArrayList<>());
    }

    @Override
    public List<ResponseEmployeeDTO> getAll(String employeeAvailability, String employmentState, String branchId, String userTypeId) {
        List<Employee> list;
        ArrayList<ResponseEmployeeDTO> arrayList = new ArrayList<>();


        boolean filterByBranch = branchId != null && !branchId.isEmpty();
        boolean filterByUserType = userTypeId != null && !userTypeId.isEmpty();

        switch (employeeAvailability) {
            case "ALL":
                switch (employmentState) {
                    case "ALL":
                        list = employeeRepo.findAll();
                        break;
                    case "EMPLOYED":
                        list = employeeRepo.findEmployed();
                        break;
                    case "TERMINATED":
                        list = employeeRepo.findTerminated();
                        break;
                    default:
                        throw new RuntimeException("Filter Not Valid");
                }
                break;
            case "ENABLED":
                switch (employmentState) {
                    case "ALL":
                        list = employeeRepo.findEnabled();
                        break;
                    case "EMPLOYED":
                        list = employeeRepo.findEnabledEmployed();
                        break;
                    case "TERMINATED":
                        list = employeeRepo.findEnabledTerminated();
                        break;
                    default:
                        throw new RuntimeException("Filter Not Valid");
                }
                break;
            case "DISABLED":
                switch (employmentState) {
                    case "ALL":
                        list = employeeRepo.findDisabled();
                        break;
                    case "EMPLOYED":
                        list = employeeRepo.findDisabledEmployed();
                        break;
                    case "TERMINATED":
                        list = employeeRepo.findDisabledTerminated();
                        break;
                    default:
                        throw new RuntimeException("Filter Not Valid");
                }
                break;
            default:
                throw new RuntimeException("Filter Not Valid");
        }


        if (filterByBranch || filterByUserType) {
            List<Employee> filteredList = new ArrayList<>();
            for (Employee employee : list) {
                if ((!filterByBranch || employee.getBranch().getBranchId().equals(branchId))
                        && (!filterByUserType || employee.getUserType().getUserTypeId().equals(userTypeId))) {
                    filteredList.add(employee);
                }
            }
            list = filteredList;
        }

        for (Employee u : list) {
            arrayList.add(new ResponseEmployeeDTO(
                    u.getEmployeeId(),
                    u.getEmployeeName(),
                    u.getEmployeeAddress(),
                    u.getEmployeeEmail(),
                    u.getEmployeeMobile(),
                    u.isEmploymentState(),
                    u.isActiveState(),
                    u.getBranch().getBranchName(),
                    u.getUserType().getUserTypeName()
            ));
        }
        return arrayList;
    }

    @Override
    public ResponseEmployeeDTO getById(String employeeId) {
        Optional<Employee> employee = employeeRepo.findById(employeeId);
        if (employee.isEmpty()) {
            throw new EntryNotFoundException("Employee Not Found");
        }
        return new ResponseEmployeeDTO(
                employee.get().getEmployeeId(),
                employee.get().getEmployeeName(),
                employee.get().getEmployeeAddress(),
                employee.get().getEmployeeEmail(),
                employee.get().getEmployeeMobile(),
                employee.get().isEmploymentState(),
                employee.get().isActiveState(),
                employee.get().getBranch().getBranchName(),
                employee.get().getUserType().getUserTypeName()
        );
    }

    @Override
    public CommonResponseDTO changeState(boolean state, String employeeId) {
        Optional<Employee> employee = employeeRepo.findById(employeeId);
        if (employee.isEmpty()) {
            throw new EntryNotFoundException("Employee Not Found");
        }
        employee.get().setActiveState(state);
        employeeRepo.save(employee.get());
        return new CommonResponseDTO(200, "changed", employeeId, new ArrayList<>());
    }

    @Override
    public CommonResponseDTO changeEmploymentState(boolean employmentState, String employeeId) {
        Optional<Employee> employee = employeeRepo.findById(employeeId);
        if (employee.isEmpty()) {
            throw new EntryNotFoundException("Employee Not Found");
        }
        employee.get().setEmploymentState(employmentState);
        employeeRepo.save(employee.get());
        return new CommonResponseDTO(200, "changed", employeeId, new ArrayList<>());
    }

    @Override
     public Object getEmployeeCount() {
         long count = employeeRepo.countEmployee();
         return new CountDTO(count);
     }


}
