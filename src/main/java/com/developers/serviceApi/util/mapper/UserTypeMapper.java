package com.developers.serviceApi.util.mapper;

import com.developers.serviceApi.dto.UserTypeDTO;
import com.developers.serviceApi.entity.UserType;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserTypeMapper {

    UserType toUserType(UserTypeDTO userTypeDTO);

    UserTypeDTO toUserTypeDto(UserType userType);
}

