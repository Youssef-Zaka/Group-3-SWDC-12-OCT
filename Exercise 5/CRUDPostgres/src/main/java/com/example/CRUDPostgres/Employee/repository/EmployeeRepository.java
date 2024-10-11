package com.example.CRUDPostgres.Employee.repository;

import com.example.CRUDPostgres.Employee.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository interface for managing Employee entities.
 */
@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
}
