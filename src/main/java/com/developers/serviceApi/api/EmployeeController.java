package com.developers.serviceApi.api;

import com.developers.serviceApi.dto.requestDTO.RequestEmployeeDTO;
import com.developers.serviceApi.dto.responseDTO.CommonResponseDTO;
import com.developers.serviceApi.service.EmployeeService;
import com.developers.serviceApi.util.StandardResponse;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.sql.SQLException;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/employees")
@CrossOrigin
public class EmployeeController {
    private final EmployeeService employeeService;

    private final Logger LOGGER = LoggerFactory.getLogger(EmployeeService.class);

    /**
     * This api provides create employee function
     *
     * @param dto
     * @param branchId
     * @param userTypeId
     * @return ResponseEntity<StandardResponse>
     * @throws SQLException
     */
    @PostMapping(path = {"/create"}, params = {"branchId", "userTypeId"})
    public ResponseEntity<StandardResponse> create(
            @Valid @RequestBody RequestEmployeeDTO dto,
            @RequestParam String branchId,
            @RequestParam String userTypeId) throws SQLException {
        LOGGER.info("EmployeeController->create method accessed");
        CommonResponseDTO responseData = employeeService.create(dto, branchId, userTypeId);
        return new ResponseEntity<>(
                new StandardResponse(
                        responseData.getCode(), responseData.getMessage(), responseData.getData()
                ),
                HttpStatus.CREATED
        );
    }

    /**
     * This api provides all data of employee
     *
     * @param employeeAvailability
     * @param employmentState
     * @param branchId
     * @param userTypeId
     * @return ResponseEntity<StandardResponse>
     * @throws SQLException
     */
    @GetMapping(path = {"/get-all"}, params = {"employeeAvailability", "employmentState", "branchId", "userTypeId"})
    public ResponseEntity<StandardResponse> getAll(
            @RequestParam String employeeAvailability,
            @RequestParam String employmentState,
            @RequestParam String branchId,
            @RequestParam String userTypeId

    ) throws SQLException {
        LOGGER.info("EmployeeController->getAll method accessed");
        return new ResponseEntity<>(
                new StandardResponse(200, "All Records Fetched",
                        employeeService.getAll(employeeAvailability, employmentState, branchId, userTypeId)),
                HttpStatus.OK
        );
    }

    /**
     * This api provides get employee count function
     *
     * @return ResponseEntity<StandardResponse>
     * @throws SQLException
     */
    @GetMapping(path = {"/get-employee-count"})
    public ResponseEntity<StandardResponse> getAllEmployeeCount(
    ) throws SQLException {
        LOGGER.info("EmployeeController->getAllEmployeeCount method accessed");
        return new ResponseEntity<>(
                new StandardResponse(200, "All Records Fetched",
                        employeeService.getEmployeeCount()),
                HttpStatus.OK
        );
    }

    /**
     * This api provides get employee by id function
     *
     * @param employeeId
     * @return ResponseEntity<StandardResponse>
     * @throws SQLException
     */
    @GetMapping(path = {"/get-by-id"}, params = {"employeeId"})
    public ResponseEntity<StandardResponse> getById(
            @RequestParam String employeeId

    ) throws SQLException {
        LOGGER.info("EmployeeController->getById method accessed");
        return new ResponseEntity<>(
                new StandardResponse(200, "All Records Fetched",
                        employeeService.getById(employeeId)),
                HttpStatus.OK
        );
    }

    /**
     * This api provides change active state of employee
     *
     * @param state
     * @param employeeId
     * @return ResponseEntity<StandardResponse>
     * @throws SQLException
     */
    @PutMapping(path = {"/change-state"}, params = {"state", "employeeId"})
    public ResponseEntity<StandardResponse> changeState(
            @RequestParam boolean state,
            @RequestParam String employeeId

    ) throws SQLException {
        LOGGER.info("EmployeeController->changeState method accessed");
        CommonResponseDTO responseData = employeeService.changeState(state, employeeId);
        return new ResponseEntity<>(
                new StandardResponse(
                        responseData.getCode(), responseData.getMessage(), responseData.getData()
                ),
                HttpStatus.CREATED
        );
    }

    /**
     * This api provides change employment state of employee
     *
     * @param employmentState
     * @param employeeId
     * @return ResponseEntity<StandardResponse>
     * @throws SQLException
     */
    @PutMapping(path = {"/change-employment-state"}, params = {"employmentState", "employeeId"})
    public ResponseEntity<StandardResponse> changeEmploymentState(
            @RequestParam boolean employmentState,
            @RequestParam String employeeId

    ) throws SQLException {
        LOGGER.info("EmployeeController->changeEmploymentState method accessed");
        CommonResponseDTO responseData = employeeService.changeEmploymentState(employmentState, employeeId);
        return new ResponseEntity<>(
                new StandardResponse(
                        responseData.getCode(), responseData.getMessage(), responseData.getData()
                ),
                HttpStatus.CREATED
        );
    }

}
