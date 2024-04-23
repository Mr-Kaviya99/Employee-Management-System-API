package com.developers.serviceApi.service.impl;

import com.developers.serviceApi.dto.BranchDTO;
import com.developers.serviceApi.dto.requestDTO.RequestBranchDTO;
import com.developers.serviceApi.dto.responseDTO.CommonResponseDTO;
import com.developers.serviceApi.dto.responseDTO.CountDTO;
import com.developers.serviceApi.dto.responseDTO.ResponseBranchDTO;
import com.developers.serviceApi.entity.Branch;
import com.developers.serviceApi.exception.EntryNotFoundException;
import com.developers.serviceApi.repo.BranchRepo;
import com.developers.serviceApi.service.BranchService;
import com.developers.serviceApi.util.Generator;
import com.developers.serviceApi.util.mapper.BranchMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.vladmihalcea.hibernate.type.util.LogUtils.LOGGER;

@RequiredArgsConstructor
@Service
public class BranchServiceImpl implements BranchService {
    private final Generator generator;
    private final BranchRepo branchRepo;
    private final BranchMapper branchMapper;

    /**
     * This method provides create branch function
     *
     * @param dto
     * @return CommonResponseDTO
     */
    @Override
    public CommonResponseDTO create(RequestBranchDTO dto) {

        LOGGER.info("BranchService->Create method accessed");

        String prefix = "SAPI-B-";
        String propertyId = generator.generateNewId(prefix, branchRepo.findLastId(prefix, prefix.length() + 1));

        BranchDTO branchDTO = new BranchDTO(
                propertyId,
                dto.getBranchName(),
                true
        );
        branchRepo.save(branchMapper.toBranch(branchDTO));
        return new CommonResponseDTO(201, " saved!", true, new ArrayList<>());
    }

    /**
     * This method provides all data of branch
     *
     * @return List<ResponseBranchDTO>
     */
    @Override
    public List<ResponseBranchDTO> getAll() {

        LOGGER.info("BranchService->getAll method accessed");

        List<Branch> list = branchRepo.findAll();
        ArrayList<ResponseBranchDTO> arrayList = new ArrayList<>();

        for (Branch u : list) {
            arrayList.add(new ResponseBranchDTO(
                    u.getBranchId(),
                    u.getBranchName(),
                    u.isActiveState()
            ));
        }
        return arrayList;
    }

    /**
     * This method provides delete branch function
     *
     * @param branchId
     * @return CommonResponseDTO
     */
    @Override
    public CommonResponseDTO delete(String branchId) {

        LOGGER.info("BranchService->delete method accessed");

        Optional<Branch> branch = branchRepo.findById(branchId);
        if (branch.isEmpty()) {
            throw new EntryNotFoundException("Branch Not Found");
        }
        branchRepo.delete(branch.get());
        return new CommonResponseDTO(204, "Record Deleted!",
                branchId, new ArrayList<>());
    }

    /**
     * This method provides get branch count function
     *
     * @return CountDTO
     */
    @Override
    public CountDTO getBranchCount() {

        LOGGER.info("BranchService->getBranchCount method accessed");

        long count = branchRepo.countBranches();
        return new CountDTO(count);
    }
}
