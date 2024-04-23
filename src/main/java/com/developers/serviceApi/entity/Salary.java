package com.developers.serviceApi.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@AllArgsConstructor
@Table(name = "salary")
public class Salary {

    @Id
    @Column(name = "salary_id", length = 45, nullable = false)
    private String salaryId;

    @Column(name = "month", length = 45)
    private String month;

    @Column(name = "amount")
    private Double amount;

    @Column(name = "paid_status")
    private boolean paidStatus;

    @ManyToOne
    @JoinColumn(name = "employee_id",referencedColumnName = "employee_id",nullable = false)
    private Employee employee;

}
