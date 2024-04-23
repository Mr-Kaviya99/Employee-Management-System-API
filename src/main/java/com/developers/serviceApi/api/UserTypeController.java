package com.developers.serviceApi.api;

import com.developers.serviceApi.dto.requestDTO.RequestUserTypeDTO;
import com.developers.serviceApi.dto.responseDTO.CommonResponseDTO;
import com.developers.serviceApi.service.UserTypeService;
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
@RequestMapping("/api/v1/user-types")
@CrossOrigin
public class UserTypeController {
    private final Logger LOGGER = LoggerFactory.getLogger(UserTypeController.class);
    private final UserTypeService userTypeService;

    /**
     * This api provides create user type function
     *
     * @param dto
     * @return ResponseEntity<StandardResponse>
     * @throws SQLException
     */
    @PostMapping(path = {"/create"})
    public ResponseEntity<StandardResponse> create(
            @Valid @RequestBody RequestUserTypeDTO dto) throws SQLException {
        LOGGER.info("UserTypeController->create method accessed");
        CommonResponseDTO responseData = userTypeService.create(dto);
        return new ResponseEntity<>(
                new StandardResponse(
                        responseData.getCode(), responseData.getMessage(), responseData.getData()
                ),
                HttpStatus.CREATED
        );
    }

    /**
     * This api provides all data of user type
     *
     * @return ResponseEntity<StandardResponse>
     * @throws SQLException
     */
    @GetMapping(path = {"/get-all"})
    public ResponseEntity<StandardResponse> getAll(
    ) throws SQLException {
        LOGGER.info("UserTypeController->getAll method accessed");
        return new ResponseEntity<>(
                new StandardResponse(200, "All Records Fetched",
                        userTypeService.getAll()),
                HttpStatus.OK
        );
    }

    /**
     * This api provides get user type count function
     *
     * @return ResponseEntity<StandardResponse>
     * @throws SQLException
     */
    @GetMapping(path = {"/get-user-type-count"})
    public ResponseEntity<StandardResponse> getAllUserTypeCount(
    ) throws SQLException {
        LOGGER.info("UserTypeController->getAllUserTypeCount method accessed");
        return new ResponseEntity<>(
                new StandardResponse(200, "All Records Fetched",
                        userTypeService.getUserTypeCount()),
                HttpStatus.OK
        );
    }

    /**
     * This api provides delete user type function
     *
     * @param userTypeId
     * @return ResponseEntity<StandardResponse>
     */
    @DeleteMapping(path = {"/{userTypeId}"})
    public ResponseEntity<StandardResponse> delete(
            @PathVariable String userTypeId) {
        LOGGER.info("UserTypeController->delete method accessed");
        CommonResponseDTO responseData = userTypeService.delete(userTypeId);
        return new ResponseEntity<>(
                new StandardResponse(
                        responseData.getCode(), responseData.getMessage(), responseData.getData()
                ),
                HttpStatus.CREATED
        );
    }


}
