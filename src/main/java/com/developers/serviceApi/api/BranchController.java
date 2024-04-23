package com.developers.serviceApi.api;

import com.developers.serviceApi.dto.requestDTO.RequestBranchDTO;
import com.developers.serviceApi.dto.responseDTO.CommonResponseDTO;
import com.developers.serviceApi.service.BranchService;
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
@RequestMapping("/api/v1/branch")
@CrossOrigin
public class BranchController {
    private final Logger LOGGER = LoggerFactory.getLogger(BranchController.class);
    private final BranchService branchService;

    /**
     * This api provides create branch function
     *
     * @param dto
     * @return ResponseEntity<StandardResponse>
     * @throws SQLException
     */
    @PostMapping(path = {"/create"})
    public ResponseEntity<StandardResponse> create(
            @Valid @RequestBody RequestBranchDTO dto) throws SQLException {
        LOGGER.info("BranchController->create method accessed");
        CommonResponseDTO responseData = branchService.create(dto);
        return new ResponseEntity<>(
                new StandardResponse(
                        responseData.getCode(), responseData.getMessage(), responseData.getData()
                ),
                HttpStatus.CREATED
        );
    }

    /**
     * This api provides all data of branch
     *
     * @return ResponseEntity<StandardResponse>
     * @throws SQLException
     */
    @GetMapping(path = {"/get-all"})
    public ResponseEntity<StandardResponse> getAll() throws SQLException {
        LOGGER.info("BranchController->getAll method accessed");
        return new ResponseEntity<>(
                new StandardResponse(200, "All Records Fetched",
                        branchService.getAll()),
                HttpStatus.OK
        );
    }

    /**
     * This api provides delete branch function
     *
     * @param branchId
     * @return ResponseEntity<StandardResponse>
     */
    @DeleteMapping(path = {"/{branchId}"})
    public ResponseEntity<StandardResponse> delete(
            @PathVariable String branchId) {
        LOGGER.info("BranchController->delete method accessed");
        CommonResponseDTO responseData = branchService.delete(branchId);
        return new ResponseEntity<>(
                new StandardResponse(
                        responseData.getCode(), responseData.getMessage(), responseData.getData()
                ),
                HttpStatus.CREATED
        );
    }

    /**
     * This api provides get branch count function
     *
     * @return ResponseEntity<StandardResponse>
     * @throws SQLException
     */
    @GetMapping(path = {"/get-branch-count"})
    public ResponseEntity<StandardResponse> getAllBranchCount(
    ) throws SQLException {
        LOGGER.info("BranchController->getAllBranchCount method accessed");
        return new ResponseEntity<>(
                new StandardResponse(200, "All Records Fetched",
                        branchService.getBranchCount()),
                HttpStatus.OK
        );
    }
}
