package com.developers.serviceApi.dto;

import com.developers.serviceApi.entity.Employee;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.Set;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BranchDTO {
    private String branchId;
    private String branchName;
    private boolean activeState;
}
