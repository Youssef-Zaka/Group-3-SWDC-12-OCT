package com.example.CRUDPostgres.Employee.controller;

import com.example.CRUDPostgres.Employee.dto.EmployeeDTO; // Import the EmployeeDTO
import com.example.CRUDPostgres.Employee.services.EmployeeService; // Import the EmployeeService
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * REST controller for managing Employee entities.
 * Provides endpoints for CRUD operations on employees.
 */
@RestController
@RequestMapping("api/employees")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService; // Inject EmployeeService

    /**
     * Adds a new employee.
     *
     * @param employeeDTO The DTO representation of the employee to be added.
     * @return ResponseEntity containing the created employee DTO and a status of 201 (Created).
     */
    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<EmployeeDTO> addEmployee(@Valid @RequestBody EmployeeDTO employeeDTO) {
        EmployeeDTO createdEmployee = employeeService.saveEmployee(employeeDTO); // Save employee and get the created DTO
        return ResponseEntity.status(201).body(createdEmployee);
    }

    /**
     * Retrieves a paginated list of employees with sorting support.
     *
     * @param pageable Pageable object with page number, size, and sorting options.
     * @return ResponseEntity containing a paginated list of EmployeeDTOs.
     */
    @GetMapping
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public ResponseEntity<Page<EmployeeDTO>> getAllEmployees(Pageable pageable) {
        Page<EmployeeDTO> employees = employeeService.getAllEmployees(pageable);
        return ResponseEntity.ok(employees);
    }

    /**
     * Retrieves an employee by ID.
     *
     * @param id The ID of the employee to retrieve.
     * @return ResponseEntity containing the employee DTO if found, or a 404 (Not Found) status if not found.
     */
    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public ResponseEntity<EmployeeDTO> getEmployeeById(@PathVariable Long id) {
        EmployeeDTO employeeDTO = employeeService.getEmployeeById(id); // Fetch employee by ID
        return ResponseEntity.ok(employeeDTO); // Return the employee DTO
    }

    /**
     * Updates an existing employee.
     *
     * @param id The ID of the employee to update.
     * @param employeeDTO The DTO representation of the employee with updated information.
     * @return ResponseEntity containing the updated employee DTO or a 404 (Not Found) status if the employee does not exist.
     */
    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<EmployeeDTO> updateEmployee(@PathVariable Long id,@Valid @RequestBody EmployeeDTO employeeDTO) {
        EmployeeDTO updatedEmployee = employeeService.updateEmployee(id, employeeDTO); // Update employee and get updated DTO
        return ResponseEntity.ok(updatedEmployee);
    }

    /**
     * Deletes an employee by ID.
     *
     * @param id The ID of the employee to be deleted.
     * @return ResponseEntity with a status of 204 (No Content) after deletion or a 404 (Not Found) status if the employee does not exist.
     */
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deleteEmployee(@PathVariable Long id) {
        employeeService.deleteEmployee(id); // Delete employee
        return ResponseEntity.noContent().build(); // Return no content response
    }

    /**
     * Retrieves employees by department ID.
     *
     * @param departmentId The ID of the department.
     * @return ResponseEntity containing a list of EmployeeDTOs associated with the given department.
     */
    @GetMapping("/department/{departmentId}")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public ResponseEntity<List<EmployeeDTO>> getEmployeesByDepartmentId(@PathVariable Long departmentId) {
        List<EmployeeDTO> employees = employeeService.getEmployeesByDepartmentId(departmentId);
        return ResponseEntity.ok(employees);
    }
}
