package com.example.CRUDPostgres.Employee.services;

import com.example.CRUDPostgres.Department.dto.DepartmentDTO;
import com.example.CRUDPostgres.Department.entity.Department;
import com.example.CRUDPostgres.Department.exceptions.DepartmentNotFoundException;
import com.example.CRUDPostgres.Department.services.DepartmentService;
import com.example.CRUDPostgres.Employee.dto.EmployeeDTO;
import com.example.CRUDPostgres.Employee.entity.Employee;
import com.example.CRUDPostgres.Employee.mapper.EmployeeMapper;
import com.example.CRUDPostgres.Employee.repository.EmployeeRepository;
import com.example.CRUDPostgres.Employee.exceptions.EmployeeNotFoundException; // Import custom exception
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.lang.NonNull;
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
        // Validate department ID
        if (employeeDTO.getDepartmentId() == null) {
            throw new DepartmentNotFoundException("Department ID is required.");
        }
        // Fetch the department based on the provided department ID
        Department department = departmentService.getDepartmentEntityById(employeeDTO.getDepartmentId());
        Employee employee = employeeMapper.toEntity(employeeDTO, department); // Pass DTO and DepartmentDTO
        employee = employeeRepository.save(employee);
        return employeeMapper.toDTO(employee); // Convert back to DTO to return
    }


    /**
     * Retrieves a paginated list of employees with sorting support.
     *
     * @param pageable The Pageable object containing pagination and sorting information.
     * @return A paginated list of EmployeeDTOs.
     */
    public Page<EmployeeDTO> getAllEmployees(@NonNull Pageable pageable) {
        Page<Employee> employeesPage = employeeRepository.findAll(pageable);
        return employeesPage.map(employeeMapper::toDTO); // Convert Page<Employee> to Page<EmployeeDTO>
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
     * @throws EmployeeNotFoundException if the employee does not exist.
     */
    public EmployeeDTO updateEmployee(Long id, EmployeeDTO employeeDTO) {
        Employee employeeToUpdate = employeeRepository.findById(id)
                .orElseThrow(() -> new EmployeeNotFoundException("Employee with ID " + id + " not found."));

        // Update fields
        employeeToUpdate.setName(employeeDTO.getName());

        // Fetch and update department if necessary
        Department department = departmentService.getDepartmentEntityById(employeeDTO.getDepartmentId());
        employeeToUpdate.setDepartment(department);

        employeeToUpdate = employeeRepository.save(employeeToUpdate); // Save updated entity
        return employeeMapper.toDTO(employeeToUpdate); // Convert back to DTO to return
    }

    /**
     * Deletes an employee by ID.
     *
     * @param id The ID of the employee to be deleted.
     * @throws EmployeeNotFoundException if the employee does not exist.
     */
    public void deleteEmployee(Long id) {
        if (!employeeRepository.existsById(id)) {
            throw new EmployeeNotFoundException("Employee with ID " + id + " not found.");
        }
        employeeRepository.deleteById(id); // Delete employee
    }

    /**
     * Retrieves employees by department ID.
     *
     * @param departmentId The ID of the department.
     * @return A list of EmployeeDTOs associated with the given department ID.
     * @throws DepartmentNotFoundException if the department does not exist.
     */
    public List<EmployeeDTO> getEmployeesByDepartmentId(Long departmentId) {
        // Check if department exists, otherwise throw an exception
        departmentService.getDepartmentEntityById(departmentId); // Will throw DepartmentNotFoundException if not found

        // Fetch employees associated with the department
        List<Employee> employees = employeeRepository.findByDepartmentId(departmentId);
        return employees.stream().map(employeeMapper::toDTO).collect(Collectors.toList());
    }
}
