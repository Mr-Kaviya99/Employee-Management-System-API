package com.developers.serviceApi.service.impl;

import com.developers.serviceApi.dto.SystemUserDTO;
import com.developers.serviceApi.entity.SystemUser;
import com.developers.serviceApi.exception.EntryNotFoundException;
import com.developers.serviceApi.repo.SystemUserRepo;
import com.developers.serviceApi.service.SystemUserService;
import com.developers.serviceApi.util.mapper.SystemUserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static com.vladmihalcea.hibernate.type.util.LogUtils.LOGGER;

@RequiredArgsConstructor
@Service
public class SystemUserServiceImpl implements SystemUserService {

    private final SystemUserRepo systemUserRepo;
    private final SystemUserMapper systemUserMapper;

    /**
     * This method provides login function
     *
     * @param userName
     * @param password
     * @return boolean
     */
    @Override
    public boolean login(String userName, String password) {

        LOGGER.info("SystemUserServiceImpl->login method accessed");

        Optional<SystemUser> user = systemUserRepo.findByUserName(userName);
        if (user.isEmpty()) {
            throw new EntryNotFoundException("User Not Found!!!!");
        }
        return user.get().getPassword().equals(password);
    }

    /**
     * This method provides initialize system user function
     */
    @Override
    public void initializeUser() {

        LOGGER.info("SystemUserServiceImpl->initializeUser method accessed");

        SystemUserDTO systemUserDTO = new SystemUserDTO(
                "SAPI-SU-1",
                "admin",
                "admin@gmail.com",
                "admin"
        );
        systemUserRepo.save(systemUserMapper.toSystemUser(systemUserDTO));
    }
}
