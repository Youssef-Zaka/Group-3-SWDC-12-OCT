package com.example.CRUDPostgres.Department.repository;

import com.example.CRUDPostgres.Department.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository interface for managing Department entities.
 */
@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long> {
    Department findByName(String name); // Method to find department by name
}
