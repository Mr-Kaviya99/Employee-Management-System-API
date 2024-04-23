package com.developers.serviceApi.util.mapper;

import com.developers.serviceApi.dto.SystemUserDTO;
import com.developers.serviceApi.entity.SystemUser;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SystemUserMapper {

    SystemUser toSystemUser(SystemUserDTO systemUserDTO);

    SystemUserDTO toSystemUserDto(SystemUser systemUser);
}
