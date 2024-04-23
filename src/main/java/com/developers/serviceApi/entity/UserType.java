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
@Table(name = "user_type")
public class UserType {

    @Id
    @Column(name = "user_type_id", length = 45, nullable = false)
    private String userTypeId;

    @Column(name = "user_type_name", length = 90)
    private String userTypeName;

    @Column(name = "active_state")
    private boolean activeState; // Assuming active_state is a tinyint(1)

    @OneToMany(mappedBy = "userType", fetch = FetchType.LAZY)
    private Set<Employee> employees;
}
