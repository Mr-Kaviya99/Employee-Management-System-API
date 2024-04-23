package com.developers.serviceApi.entity;

import lombok.*;
import javax.persistence.*;

@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@AllArgsConstructor
@Table(name = "system_user")
public class SystemUser {

    @Id
    @Column(name = "system_user_id", length = 45, nullable = false)
    private String systemUserId;

    @Column(name = "user_name", length = 45)
    private String userName;

    @Column(name = "system_user_email", length = 250)
    private String systemUserEmail;

    @Column(name = "password", length = 100)
    private String password;


}