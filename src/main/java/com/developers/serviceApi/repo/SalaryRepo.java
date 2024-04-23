package com.developers.serviceApi.repo;

import com.developers.serviceApi.entity.Salary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SalaryRepo extends JpaRepository<Salary, String> {

    @Query(value = "SELECT salary_id FROM salary WHERE salary_id like ?% " +
            "ORDER BY CAST(SUBSTRING(salary_id,?) AS UNSIGNED) DESC LIMIT 1", nativeQuery = true)
    String findLastId(String prefix, int i);
    @Query(value = "SELECT * FROM salary WHERE month like %?1% ", nativeQuery = true)
    List<Salary> findBySearchText(String searchText);

    @Query(value = "SELECT * FROM salary WHERE employee_id=?1", nativeQuery = true)
    List<Salary> findByEmployeeId(String employeeId);
}
