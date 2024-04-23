package com.developers.serviceApi.service.impl;


import com.developers.serviceApi.dto.UserTypeDTO;
import com.developers.serviceApi.dto.requestDTO.RequestUserTypeDTO;
import com.developers.serviceApi.dto.responseDTO.CommonResponseDTO;
import com.developers.serviceApi.dto.responseDTO.CountDTO;
import com.developers.serviceApi.dto.responseDTO.ResponseUserTypeDTO;
import com.developers.serviceApi.entity.UserType;
import com.developers.serviceApi.exception.EntryNotFoundException;
import com.developers.serviceApi.repo.UserTypeRepo;
import com.developers.serviceApi.service.UserTypeService;
import com.developers.serviceApi.util.Generator;
import com.developers.serviceApi.util.mapper.UserTypeMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserTypeServiceImpl implements UserTypeService {
    private final Generator generator;
    private final UserTypeRepo userTypeRepo;
    private final UserTypeMapper userTypeMapper;

    public UserTypeServiceImpl(Generator generator, UserTypeRepo userTypeRepo, UserTypeMapper userTypeMapper) {
        this.generator = generator;
        this.userTypeRepo = userTypeRepo;
        this.userTypeMapper = userTypeMapper;
    }

    @Override
    public CommonResponseDTO create(RequestUserTypeDTO dto) {

        //--------------------------------------------------------------------------------------------------------------
        String prefix="SAPI-UT-";
        String propertyId = generator.generateNewId(prefix,userTypeRepo.findLastId(prefix,prefix.length()+1));
        //---------------------------------------------------------------------

        UserTypeDTO userTypeDTO = new UserTypeDTO(
                propertyId,
                dto.getUserTypeName(),
                true

        );
        userTypeRepo.save(userTypeMapper.toUserType(userTypeDTO));
        return new CommonResponseDTO(201, "User Type saved!", true, new ArrayList<>());
    }

    @Override
    public List<ResponseUserTypeDTO> getAll() {
        List<UserType> list = userTypeRepo.findAll();
        ArrayList<ResponseUserTypeDTO> arrayList = new ArrayList<>();

        for(UserType u :list){
            arrayList.add(new ResponseUserTypeDTO(
                    u.getUserTypeId(),
                    u.getUserTypeName(),
                    u.isActiveState()
            ));
        }
        return arrayList;
    }

    @Override
    public CommonResponseDTO delete(String userTypeId) {
        Optional<UserType> userType = userTypeRepo.findById(userTypeId);
        if(userType.isEmpty()){
            throw new EntryNotFoundException("User Type Not Found");
        }
        userTypeRepo.delete(userType.get());
        return new CommonResponseDTO(204, "Record Deleted!",
                userTypeId, new ArrayList<>());
    }

    @Override
     public Object getUserTypeCount() {
         long count = userTypeRepo.countUser();
         return new CountDTO(count);
     }
}
