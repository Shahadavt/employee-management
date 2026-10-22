

package com.employee.employeemanagement.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.employee.employeemanagement.entity.Employee;

public interface EmployeeRepository
        extends JpaRepository<Employee, Long> {

    @Query("""
        SELECT e FROM Employee e
        WHERE (
            :search IS NULL OR :search = ''
            OR LOWER(e.name) LIKE LOWER(CONCAT('%', :search, '%'))
            OR LOWER(e.email) LIKE LOWER(CONCAT('%', :search, '%'))
            OR LOWER(e.department) LIKE LOWER(CONCAT('%', :search, '%'))
        )
        AND (
            :status IS NULL OR :status = ''
            OR UPPER(e.status) = UPPER(:status)
        )
        """)
    Page<Employee> searchEmployees(
            @Param("search") String search,
            @Param("status") String status,
            Pageable pageable);
    List<Employee> findByNameContainingIgnoreCase(String name);
}
