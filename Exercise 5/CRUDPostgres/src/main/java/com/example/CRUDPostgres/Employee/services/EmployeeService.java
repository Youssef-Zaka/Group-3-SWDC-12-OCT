package com.example.CRUDPostgres.Employee.services;

import com.example.CRUDPostgres.Employee.entity.Employee;
import com.example.CRUDPostgres.Employee.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    public Employee SaveEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    public List<Employee> GetAllEmployees() {
        return employeeRepository.findAll();
    }

    public Optional<Employee> GetEmployeeById(Long id) {
        return employeeRepository.findById(id);
    }

    public Employee UpdateEmployee(Long id, Employee employee) {
        Employee employee1 = employeeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Employee not found"));

        employee1.setName(employee.getName());
        employee1.setDepartment(employee.getDepartment());
        return employeeRepository.save(employee1);
    }

    public void DeleteEmployee(Long id) {
        employeeRepository.deleteById(id);
    }
}
