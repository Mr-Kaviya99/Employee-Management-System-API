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
@Table(name = "employee")
public class Employee {

    @Id
    @Column(name = "employee_id", length = 45, nullable = false)
    private String employeeId;

    @Column(name = "employee_name", length = 100)
    private String employeeName;

    @Column(name = "employee_address", length = 750)
    private String employeeAddress;

    @Column(name = "employee_email", length = 250)
    private String employeeEmail;

    @Column(name = "employee_mobile", length = 45)
    private String employeeMobile;


    @Column(name = "employment_state")
    private boolean employmentState;

    @Column(name = "active_state")
    private boolean activeState;

    @ManyToOne
    @JoinColumn(name = "user_type_id",referencedColumnName = "user_type_id",nullable = false)
    private UserType userType;

    @ManyToOne
    @JoinColumn(name = "branch_id",referencedColumnName = "branch_id",nullable = false)
    private Branch branch;

    @OneToMany(mappedBy = "employee", fetch = FetchType.LAZY)
    private Set<Salary> salarySet;


}
