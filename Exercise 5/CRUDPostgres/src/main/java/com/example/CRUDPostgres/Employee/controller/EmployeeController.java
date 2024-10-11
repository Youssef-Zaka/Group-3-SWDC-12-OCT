package com.example.CRUDPostgres.Employee.controller;

import com.example.CRUDPostgres.Employee.dto.EmployeeDTO; // Import the EmployeeDTO
import com.example.CRUDPostgres.Employee.services.EmployeeService; // Import the EmployeeService
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<EmployeeDTO> addEmployee(@RequestBody EmployeeDTO employeeDTO) {
        EmployeeDTO createdEmployee = employeeService.saveEmployee(employeeDTO); // Save employee and get the created DTO
        return ResponseEntity.status(201).body(createdEmployee);
    }

    /**
     * Retrieves all employees.
     *
     * @return ResponseEntity containing a list of all employee DTOs and a status of 200 (OK).
     */
    @GetMapping
    public ResponseEntity<List<EmployeeDTO>> getAllEmployees() {
        List<EmployeeDTO> employees = employeeService.getAllEmployees(); // Fetch all employees as DTOs
        return ResponseEntity.ok(employees);
    }

    /**
     * Retrieves an employee by ID.
     *
     * @param id The ID of the employee to retrieve.
     * @return ResponseEntity containing the employee DTO if found, or a 404 (Not Found) status if not found.
     */
    @GetMapping("/{id}")
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
    public ResponseEntity<EmployeeDTO> updateEmployee(@PathVariable Long id, @RequestBody EmployeeDTO employeeDTO) {
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
    public ResponseEntity<Void> deleteEmployee(@PathVariable Long id) {
        employeeService.deleteEmployee(id); // Delete employee
        return ResponseEntity.noContent().build(); // Return no content response
    }
}
