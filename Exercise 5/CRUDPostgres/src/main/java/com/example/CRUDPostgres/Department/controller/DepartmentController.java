package com.example.CRUDPostgres.Department.controller;

import com.example.CRUDPostgres.Department.dto.DepartmentDTO; // Import the DepartmentDTO
import com.example.CRUDPostgres.Department.services.DepartmentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * REST controller for managing Department entities.
 * Provides endpoints for CRUD operations on departments.
 */
@RestController
@RequestMapping("/departments")
public class DepartmentController {

    @Autowired
    private DepartmentService departmentService;

    /**
     * Creates a new department.
     *
     * @param departmentDTO The DTO representation of the department to be created.
     * @return ResponseEntity containing the created department DTO and a status of 201 (Created).
     */
    @PostMapping
    public ResponseEntity<DepartmentDTO> createDepartment(@Valid @RequestBody DepartmentDTO departmentDTO) {
        DepartmentDTO createdDepartment = departmentService.createDepartment(departmentDTO);
        return ResponseEntity.status(201).body(createdDepartment);
    }

    /**
     * Retrieves all departments.
     *
     * @return ResponseEntity containing a list of all department DTOs and a status of 200 (OK).
     */
    @GetMapping
    public ResponseEntity<List<DepartmentDTO>> getAllDepartments() {
        List<DepartmentDTO> departments = departmentService.getAllDepartments();
        return ResponseEntity.ok(departments);
    }

    /**
     * Retrieves a department by its ID.
     *
     * @param id The ID of the department to retrieve.
     * @return ResponseEntity containing the department DTO if found, or a 404 (Not Found) status if not found.
     */
    @GetMapping("/{id}")
    public ResponseEntity<DepartmentDTO> getDepartmentById(@PathVariable Long id) {
        DepartmentDTO department = departmentService.getDepartmentById(id);
        return department != null ? ResponseEntity.ok(department) : ResponseEntity.notFound().build();
    }

    /**
     * Deletes a department by its ID.
     *
     * @param id The ID of the department to be deleted.
     * @return ResponseEntity with a status of 204 (No Content) after deletion.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDepartment(@PathVariable Long id) {
        departmentService.deleteDepartment(id);
        return ResponseEntity.noContent().build();
    }
}
