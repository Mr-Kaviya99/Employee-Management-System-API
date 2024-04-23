package com.developers.serviceApi.repo;

import com.developers.serviceApi.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepo extends JpaRepository<Employee,String> {
    @Query(value = "SELECT employee_id FROM employee WHERE employee_id like ?% " +
            "ORDER BY CAST(SUBSTRING(employee_id,?) AS UNSIGNED) DESC LIMIT 1", nativeQuery = true)
    String findLastId(String prefix, int i);

    @Query(value = "SELECT * FROM employee WHERE employment_state=1 ", nativeQuery = true)
    List<Employee> findEmployed();

    @Query(value = "SELECT * FROM employee WHERE employment_state=0 ", nativeQuery = true)
    List<Employee> findTerminated();


    @Query(value = "SELECT * FROM employee WHERE active_state=1 ", nativeQuery = true)
    List<Employee> findEnabled();

    @Query(value = "SELECT * FROM employee WHERE active_state=1 AND employment_state=1", nativeQuery = true)
    List<Employee> findEnabledEmployed();

    @Query(value = "SELECT * FROM employee WHERE active_state=1 AND employment_state=0", nativeQuery = true)
    List<Employee> findEnabledTerminated();


    @Query(value = "SELECT * FROM employee WHERE active_state=0 ", nativeQuery = true)
    List<Employee> findDisabled();

    @Query(value = "SELECT * FROM employee WHERE active_state=0 AND employment_state=1", nativeQuery = true)
    List<Employee> findDisabledEmployed();

    @Query(value = "SELECT * FROM employee WHERE active_state=0 AND employment_state=0", nativeQuery = true)
    List<Employee> findDisabledTerminated();

    @Query(value = "SELECT * FROM employee WHERE user_type_id=?1 AND active_state=1 AND employment_state=1", nativeQuery = true)
    List<Employee> findByUserTypeId(String userTypeId);

    @Query(value = "SELECT COUNT(*) FROM employee", nativeQuery = true)
    long countEmployee();

}
