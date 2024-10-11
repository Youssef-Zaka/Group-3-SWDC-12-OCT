package com.example.CRUDPostgres.Employee.services;

import com.example.CRUDPostgres.Department.dto.DepartmentDTO;
import com.example.CRUDPostgres.Department.entity.Department;
import com.example.CRUDPostgres.Department.services.DepartmentService;
import com.example.CRUDPostgres.Employee.dto.EmployeeDTO;
import com.example.CRUDPostgres.Employee.entity.Employee;
import com.example.CRUDPostgres.Employee.mapper.EmployeeMapper;
import com.example.CRUDPostgres.Employee.repository.EmployeeRepository;
import com.example.CRUDPostgres.Employee.exceptions.EmployeeNotFoundException; // Import custom exception
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private DepartmentService departmentService;

    @Autowired
    private EmployeeMapper employeeMapper; // Inject EmployeeMapper

    /**
     * Saves a new employee.
     *
     * @param employeeDTO The DTO representation of the employee to be saved.
     * @return The saved employee as a DTO.
     */
    public EmployeeDTO saveEmployee(EmployeeDTO employeeDTO) {

        // Fetch the department based on the provided department ID
        Department department = departmentService.getDepartmentEntityById(employeeDTO.getDepartmentId());
        Employee employee = employeeMapper.toEntity(employeeDTO, department); // Pass DTO and DepartmentDTO
        employee = employeeRepository.save(employee);
        return employeeMapper.toDTO(employee); // Convert back to DTO to return
    }


    /**
     * Retrieves all employees.
     *
     * @return A list of all employees as DTOs.
     */
    public List<EmployeeDTO> getAllEmployees() {
        return employeeRepository.findAll().stream()
                .map(employeeMapper::toDTO) // Convert each entity to DTO
                .collect(Collectors.toList());
    }

    /**
     * Retrieves an employee by ID.
     *
     * @param id The ID of the employee to retrieve.
     * @return The employee as a DTO if found, or throws an exception if not found.
     */
    public EmployeeDTO getEmployeeById(Long id) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new EmployeeNotFoundException("Employee with ID " + id + " not found."));
        return employeeMapper.toDTO(employee); // Convert entity to DTO
    }

    /**
     * Updates an existing employee.
     *
     * @param id The ID of the employee to update.
     * @param employeeDTO The DTO representation of the employee with updated information.
     * @return The updated employee as a DTO.
     */
    public EmployeeDTO updateEmployee(Long id, EmployeeDTO employeeDTO) {
        Employee employeeToUpdate = employeeRepository.findById(id)
                .orElseThrow(() -> new EmployeeNotFoundException("Employee with ID " + id + " not found."));

        // Update fields
        employeeToUpdate.setName(employeeDTO.getName());
        // Set department if needed (could use a DepartmentDTO or ID)
        // employeeToUpdate.setDepartment(employeeDTO.getDepartment()); // Uncomment if using department in DTO

        employeeToUpdate = employeeRepository.save(employeeToUpdate); // Save updated entity
        return employeeMapper.toDTO(employeeToUpdate); // Convert back to DTO to return
    }

    /**
     * Deletes an employee by ID.
     *
     * @param id The ID of the employee to be deleted.
     */
    public void deleteEmployee(Long id) {
        if (!employeeRepository.existsById(id)) {
            throw new EmployeeNotFoundException("Employee with ID " + id + " not found.");
        }
        employeeRepository.deleteById(id); // Delete employee
    }
}
