package com.developers.serviceApi.api;

import com.developers.serviceApi.service.SystemUserService;
import com.developers.serviceApi.util.StandardResponse;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/system-users")
@CrossOrigin
public class SystemUserController {
    private final SystemUserService systemUserService;
    private final Logger LOGGER = LoggerFactory.getLogger(SystemUserController.class);

    /**
     * This api provides login function
     *
     * @param userName
     * @param password
     * @return ResponseEntity<StandardResponse>
     * @throws SQLException
     */
    @GetMapping(path = {"/login"}, params = {"userName", "password"})
    public ResponseEntity<StandardResponse> login(
            @RequestParam String userName,
            @RequestParam String password
    ) throws SQLException {
        LOGGER.info("SystemUserController->login method accessed");
        return new ResponseEntity<>(
                new StandardResponse(200, "logged in",
                        systemUserService.login(userName, password)),
                HttpStatus.OK
        );
    }


}
