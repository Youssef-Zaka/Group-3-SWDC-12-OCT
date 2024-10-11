package com.example.CRUDPostgres.Employee.mapper;

import com.example.CRUDPostgres.Department.entity.Department;
import com.example.CRUDPostgres.Employee.dto.EmployeeDTO;
import com.example.CRUDPostgres.Employee.entity.Employee;
import org.springframework.stereotype.Component;

@Component // This makes it a Spring-managed bean
public class EmployeeMapper {

    // Convert Employee entity to EmployeeDTO
    public EmployeeDTO toDTO(Employee employee) {
        EmployeeDTO dto = new EmployeeDTO();
        dto.setId(employee.getId());
        dto.setName(employee.getName());

        // Set department ID if the department exists
        if (employee.getDepartment() != null) {
            dto.setDepartmentId(employee.getDepartment().getId());
        }

        return dto;
    }

    // Convert EmployeeDTO to Employee entity
    public Employee toEntity(EmployeeDTO dto, Department department) {
        Employee employee = new Employee();
        employee.setId(dto.getId());
        employee.setName(dto.getName());

        // Set the department using the provided Department entity
        if (department != null) {
            employee.setDepartment(department);
        } else {
            throw new IllegalArgumentException("Department cannot be null");
        }

        return employee;
    }
}
