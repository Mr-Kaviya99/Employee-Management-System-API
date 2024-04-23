package com.developers.serviceApi.repo;

import com.developers.serviceApi.entity.UserType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserTypeRepo extends JpaRepository<UserType, String> {

    @Query(value = "SELECT user_type_id FROM user_type WHERE user_type_id like ?% " +
            "ORDER BY CAST(SUBSTRING(user_type_id,?) AS UNSIGNED) DESC LIMIT 1", nativeQuery = true)
    String findLastId(String prefix, int i);

    @Query(value = "SELECT COUNT(*) FROM user_type", nativeQuery = true)
    long countUser();
}
