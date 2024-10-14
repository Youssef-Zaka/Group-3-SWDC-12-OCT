package com.example.CRUDPostgres.Employee.repository;

import com.example.CRUDPostgres.Employee.entity.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repository interface for managing Employee entities.
 */
@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    // Custom query to find employees by department ID
    List<Employee> findByDepartmentId(Long departmentId);

    // Pagination and sorting method with explicit @NonNull annotation for Pageable
    @NonNull
    Page<Employee> findAll(@NonNull Pageable pageable);
}
