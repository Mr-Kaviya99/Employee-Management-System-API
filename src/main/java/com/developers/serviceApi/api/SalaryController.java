package com.developers.serviceApi.api;

import com.developers.serviceApi.dto.requestDTO.RequestSalaryDTO;
import com.developers.serviceApi.dto.responseDTO.CommonResponseDTO;
import com.developers.serviceApi.service.SalaryService;
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
@RequestMapping("/api/v1/salaries")
@CrossOrigin
public class SalaryController {

    private final SalaryService salaryService;
    private final Logger LOGGER = LoggerFactory.getLogger(SalaryController.class);

    @PostMapping(path = {"/create"}, params = {"userTypeId"})
    public ResponseEntity<StandardResponse> create(
            @Valid @RequestBody RequestSalaryDTO dto,
            @RequestParam String userTypeId) throws SQLException {
        LOGGER.info("Resource creation request received");
        CommonResponseDTO responseData = salaryService.create(dto, userTypeId);
        return new ResponseEntity<>(
                new StandardResponse(
                        responseData.getCode(), responseData.getMessage(), responseData.getData()
                ),
                HttpStatus.CREATED
        );
    }

    @GetMapping(path = {"/get-all"}, params = {"month"})
    public ResponseEntity<StandardResponse> getAll(
            @RequestParam String month
    ) throws SQLException {
        LOGGER.info("Request received for get all ");
        return new ResponseEntity<>(
                new StandardResponse(200, "All Records Fetched",
                        salaryService.getAll(month)),
                HttpStatus.OK
        );
    }
}
