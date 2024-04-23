package com.developers.serviceApi.repo;

import com.developers.serviceApi.entity.SystemUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SystemUserRepo extends JpaRepository<SystemUser, String> {
    @Query(value = "SELECT * FROM system_user WHERE user_name =?1  or system_user_email=?1", nativeQuery = true)
    Optional<SystemUser> findByUserName(String userName);
}
