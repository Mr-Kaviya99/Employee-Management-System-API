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
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.vladmihalcea.hibernate.type.util.LogUtils.LOGGER;

@RequiredArgsConstructor
@Service
public class EmployeeServiceImpl implements EmployeeService {
    private final Generator generator;
    private final EmployeeRepo employeeRepo;
    private final EmployeeMapper employeeMapper;
    private final BranchRepo branchRepo;
    private final BranchMapper branchMapper;
    private final UserTypeRepo userTypeRepo;
    private final UserTypeMapper userTypeMapper;

    /**
     * This method provides create employee function
     *
     * @param dto
     * @param branchId
     * @param userTypeId
     * @return CommonResponseDTO
     */
    @Override
    public CommonResponseDTO create(RequestEmployeeDTO dto, String branchId, String userTypeId) {

        LOGGER.info("EmployeeService->Create method accessed");

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

    /**
     * This method provides get all employee function
     *
     * @param employeeAvailability
     * @param employmentState
     * @param branchId
     * @param userTypeId
     * @return List<ResponseEmployeeDTO>
     */
    @Override
    public List<ResponseEmployeeDTO> getAll(String employeeAvailability, String employmentState, String branchId, String userTypeId) {

        LOGGER.info("EmployeeService->getAll method accessed");

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

    /**
     * This method provides get employee by id function
     *
     * @param employeeId
     * @return ResponseEmployeeDTO
     */
    @Override
    public ResponseEmployeeDTO getById(String employeeId) {

        LOGGER.info("EmployeeService->getById method accessed");

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

    /**
     * This method provides change employee active status function
     *
     * @param state
     * @param employeeId
     * @return CommonResponseDTO
     */
    @Override
    public CommonResponseDTO changeState(boolean state, String employeeId) {

        LOGGER.info("EmployeeService->changeState method accessed");

        Optional<Employee> employee = employeeRepo.findById(employeeId);
        if (employee.isEmpty()) {
            throw new EntryNotFoundException("Employee Not Found");
        }
        employee.get().setActiveState(state);
        employeeRepo.save(employee.get());
        return new CommonResponseDTO(200, "changed", employeeId, new ArrayList<>());
    }

    /**
     * This method provides change employee employment state function
     *
     * @param employmentState
     * @param employeeId
     * @return CommonResponseDTO
     */
    @Override
    public CommonResponseDTO changeEmploymentState(boolean employmentState, String employeeId) {

        LOGGER.info("EmployeeService->changeEmploymentState method accessed");

        Optional<Employee> employee = employeeRepo.findById(employeeId);
        if (employee.isEmpty()) {
            throw new EntryNotFoundException("Employee Not Found");
        }
        employee.get().setEmploymentState(employmentState);
        employeeRepo.save(employee.get());
        return new CommonResponseDTO(200, "changed", employeeId, new ArrayList<>());
    }

    /**
     * This method provides get employee count function
     *
     * @return Object
     */
    @Override
    public Object getEmployeeCount() {

        LOGGER.info("EmployeeService->getEmployeeCount method accessed");

        long count = employeeRepo.countEmployee();
        return new CountDTO(count);
    }


}
