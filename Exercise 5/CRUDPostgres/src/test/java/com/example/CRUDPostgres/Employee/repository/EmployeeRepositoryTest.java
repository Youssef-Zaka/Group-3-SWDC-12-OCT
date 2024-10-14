package com.example.CRUDPostgres.Employee.repository;

import com.example.CRUDPostgres.Department.entity.Department;
import com.example.CRUDPostgres.Department.repository.DepartmentRepository;
import com.example.CRUDPostgres.Employee.entity.Employee;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class EmployeeRepositoryTest {

    @Autowired
    private EmployeeRepository employeeRepository;


    @Autowired
    private DepartmentRepository departmentRepository;


    @BeforeEach
    void setUp() {
        // Create and save the Department first
        Department department1 = new Department();
        department1.setName("HR");
        departmentRepository.save(department1); // Save the department before using it in Employee

        Department department2 = new Department();
        department2.setName("IT");
        departmentRepository.save(department2); // Save the department

        // Now you can create Employees associated with the saved departments
        Employee employee1 = new Employee();
        employee1.setName("John Doe");
        employee1.setDepartment(department1); // Associate with the saved department

        Employee employee2 = new Employee();
        employee2.setName("Jane Smith");
        employee2.setDepartment(department1); // Associate with the saved department

        Employee employee3 = new Employee();
        employee3.setName("Michael Brown");
        employee3.setDepartment(department2); // Associate with the saved department

        // Save all employees to the repository
        employeeRepository.save(employee1);
        employeeRepository.save(employee2);
        employeeRepository.save(employee3);
    }

    /**
     * Test for custom query: find employees by department ID.
     */
    @Test
    void testFindByDepartmentId() {
        // Fetch employees for the first department (HR)
        List<Employee> employeesInHR = employeeRepository.findByDepartmentId(1L);

        // Assertions to check if the right employees are retrieved
        assertThat(employeesInHR).hasSize(2);
        assertThat(employeesInHR).extracting(Employee::getName)
                .containsExactlyInAnyOrder("John Doe", "Jane Smith");
    }

    /**
     * Test pagination and sorting functionality.
     */
    @Test
    void testFindAllWithPaginationAndSorting() {
        // Page request with sorting by name in descending order
        PageRequest pageRequest = PageRequest.of(0, 2, Sort.by("name").descending());

        // Fetch paginated and sorted employees
        Page<Employee> employeePage = employeeRepository.findAll(pageRequest);

        // Assertions to check if pagination and sorting work correctly
        assertThat(employeePage.getTotalElements()).isEqualTo(3);
        assertThat(employeePage.getTotalPages()).isEqualTo(2);
        assertThat(employeePage.getContent()).extracting(Employee::getName)
                .containsExactly("Michael Brown", "John Doe"); // Sorted descending by name
    }

    /**
     * Test for custom query returning an empty list when department ID doesn't exist.
     */
    @Test
    void testFindByDepartmentId_NoEmployees() {
        // Fetch employees for a non-existent department (ID 99)
        List<Employee> employees = employeeRepository.findByDepartmentId(99L);

        // Assert that no employees are returned
        assertThat(employees).isEmpty();
    }

    /**
     * Test pagination when the page exceeds the total number of records.
     */
    @Test
    void testFindAllWithPaginationExceedingTotalPages() {
        // Request page 10, where no records should exist
        PageRequest pageRequest = PageRequest.of(10, 2);
        Page<Employee> employeePage = employeeRepository.findAll(pageRequest);

        // Assert that no content is found on page 10
        assertThat(employeePage.getContent()).isEmpty();
    }
}