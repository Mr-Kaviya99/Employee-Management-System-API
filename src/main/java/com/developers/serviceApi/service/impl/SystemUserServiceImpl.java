package com.developers.serviceApi.service.impl;

import com.developers.serviceApi.dto.SystemUserDTO;
import com.developers.serviceApi.entity.SystemUser;
import com.developers.serviceApi.exception.EntryNotFoundException;
import com.developers.serviceApi.repo.SystemUserRepo;
import com.developers.serviceApi.service.SystemUserService;
import com.developers.serviceApi.util.mapper.SystemUserMapper;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SystemUserServiceImpl implements SystemUserService {

    private final SystemUserRepo systemUserRepo;
    private final SystemUserMapper systemUserMapper;

    public SystemUserServiceImpl(SystemUserRepo systemUserRepo, SystemUserMapper systemUserMapper) {
        this.systemUserRepo = systemUserRepo;

        this.systemUserMapper = systemUserMapper;
    }

    @Override
    public boolean login(String userName, String password) {
        Optional<SystemUser> user= systemUserRepo.findByUserName(userName);
        if(user.isEmpty()){
            throw new EntryNotFoundException("User Not Found!!!!");
        }
        return user.get().getPassword().equals(password);
    }

    @Override
    public void initializeUser() {
        SystemUserDTO systemUserDTO = new SystemUserDTO(
            "SAPI-SU-1",
                "admin",
                "admin@gmail.com",
                "admin"
        );
        systemUserRepo.save(systemUserMapper.toSystemUser(systemUserDTO));
    }
}
