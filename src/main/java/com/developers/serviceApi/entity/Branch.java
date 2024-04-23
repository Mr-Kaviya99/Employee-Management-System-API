package com.developers.serviceApi.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@AllArgsConstructor
@Table(name = "branch")
public class Branch {

    @Id
    @Column(name = "branch_id", length = 45, nullable = false)
    private String branchId;

    @Column(name = "branch_name", length = 90)
    private String branchName;

    @Column(name = "active_state", length = 45)
    private boolean activeState;

    @OneToMany(mappedBy = "branch", fetch = FetchType.LAZY)
    private Set<Employee> employeeSet;


}
