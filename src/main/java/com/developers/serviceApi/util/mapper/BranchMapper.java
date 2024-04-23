package com.developers.serviceApi.util.mapper;

import com.developers.serviceApi.dto.BranchDTO;
import com.developers.serviceApi.entity.Branch;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BranchMapper {

    Branch toBranch(BranchDTO branchDTO);
    BranchDTO toBranchDTO(Branch branch);
}
