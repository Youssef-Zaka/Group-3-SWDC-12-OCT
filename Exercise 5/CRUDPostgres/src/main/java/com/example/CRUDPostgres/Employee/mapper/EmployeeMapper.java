package com.example.CRUDPostgres.Employee.mapper;

import com.example.CRUDPostgres.Department.dto.DepartmentDTO;
import com.example.CRUDPostgres.Department.entity.Department;
import com.example.CRUDPostgres.Department.exceptions.DepartmentNotFoundException;
import com.example.CRUDPostgres.Department.mapper.DepartmentMapper; // Import the DepartmentMapper
import com.example.CRUDPostgres.Employee.dto.EmployeeDTO;
import com.example.CRUDPostgres.Employee.entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Mapper class for converting between Employee entities and EmployeeDTOs.
 * This class handles the mapping of Employee data to and from the Data Transfer Object (DTO) representation.
 */
@Component // This makes it a Spring-managed bean
public class EmployeeMapper {

    @Autowired
    private DepartmentMapper departmentMapper; // Inject DepartmentMapper

    /**
     * Converts an Employee entity to an EmployeeDTO.
     *
     * @param employee The Employee entity to convert.
     * @return The corresponding EmployeeDTO representation of the Employee entity.
     */
    public EmployeeDTO toDTO(Employee employee) {
        EmployeeDTO dto = new EmployeeDTO();
        dto.setId(employee.getId());
        dto.setName(employee.getName());

        // Set department ID if the department exists
        if (employee.getDepartment() != null) {
            dto.setDepartmentId(employee.getDepartment().getId()); // Use the department's ID
        }

        return dto;
    }

    /**
     * Converts an EmployeeDTO to an Employee entity.
     *
     * @param dto The EmployeeDTO to convert.
     * @param department The Department associated with the Employee.
     * @return The corresponding Employee entity.
     * @throws DepartmentNotFoundException if the provided DepartmentDTO is null.
     */
    public Employee toEntity(EmployeeDTO dto, Department department) {
        Employee employee = new Employee();
        employee.setId(dto.getId());
        employee.setName(dto.getName());

        // Set the provided Department object directly
        if (department != null) {
            employee.setDepartment(department); // Associate the existing department with the employee
        } else {
            throw new DepartmentNotFoundException("Department cannot be null");
        }

        return employee;
    }
}
