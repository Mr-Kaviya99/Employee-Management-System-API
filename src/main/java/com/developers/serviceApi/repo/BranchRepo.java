package com.developers.serviceApi.repo;

import com.developers.serviceApi.entity.Branch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


@Repository
public interface BranchRepo extends JpaRepository<Branch,String> {
    @Query(value = "SELECT branch_id FROM branch WHERE branch_id like ?% " +
            "ORDER BY CAST(SUBSTRING(branch_id,?) AS UNSIGNED) DESC LIMIT 1", nativeQuery = true)
    String findLastId(String prefix, int i);

    @Query("SELECT COUNT(*) FROM Branch")
    long countBranches();
}
