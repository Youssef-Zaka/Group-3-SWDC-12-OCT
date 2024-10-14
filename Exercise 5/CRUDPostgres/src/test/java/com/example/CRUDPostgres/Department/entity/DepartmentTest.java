package com.example.CRUDPostgres.Department.entity;


import com.example.CRUDPostgres.Employee.entity.Employee;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

public class DepartmentTest {

    private Validator validator;

    @BeforeEach
    public void setUp() {
        // Setting up the validator for bean validation tests
        try (ValidatorFactory factory = Validation.buildDefaultValidatorFactory()) {
            validator = factory.getValidator();
        }
    }

    @Test
    public void testDepartmentValid() {
        // Creating a valid department object
        Department department = new Department(1L, "HR", new HashSet<>());

        // Validating the object (should pass with no violations)
        Set<ConstraintViolation<Department>> violations = validator.validate(department);

        // Asserting that no validation violations occurred
        assertTrue(violations.isEmpty(), "Expected no validation violations");
    }

    @Test
    public void testDepartmentNameBlank() {
        // Creating a department object with a blank name (violating @NotBlank)
        Department department = new Department(1L, "", new HashSet<>());

        // Validating the object (should fail with a blank name violation)
        Set<ConstraintViolation<Department>> violations = validator.validate(department);

        // Asserting that the violation occurred and its message matches the expectation
        assertEquals(2, violations.size(), "Expected Two validation violation for blank name");
    }

    @Test
    public void testDepartmentNameTooShort() {
        // Creating a department object with a name that is too short (violating @Size)
        Department department = new Department(1L, "A", new HashSet<>());

        // Validating the object (should fail with a size violation)
        Set<ConstraintViolation<Department>> violations = validator.validate(department);

        // Asserting that the violation occurred and its message matches the expectation
        assertEquals(1, violations.size(), "Expected one validation violation for short name");
        String violationMessage = violations.stream().map(ConstraintViolation::getMessage).collect(Collectors.joining());
        assertEquals("Name must be at least 2 characters long", violationMessage, "Expected size violation message");
    }

    @Test
    public void testDepartmentHasEmployees() {
        // Creating a mock set of employees
        Set<Employee> employees = new HashSet<>();
        employees.add(new Employee(1L, "John Doe", null)); // Employee without department
        employees.add(new Employee(2L, "Jane Smith", null)); // Employee without department

        // Creating a department and assigning employees to it
        Department department = new Department(1L, "HR", employees);

        // Asserting that the department has employees
        assertNotNull(department.getEmployees(), "Expected employees set to be not null");
        assertEquals(2, department.getEmployees().size(), "Expected 2 employees in the department");
    }

    @Test
    public void testDepartmentWithoutEmployees() {
        // Creating a department with no employees
        Department department = new Department(1L, "HR", new HashSet<>());

        // Asserting that the department has no employees
        assertNotNull(department.getEmployees(), "Expected employees set to be not null");
        assertTrue(department.getEmployees().isEmpty(), "Expected no employees in the department");
    }

    @Test
    public void testDepartmentToString() {
        // Creating a department object to test the Lombok-generated toString method
        Department department = new Department(1L, "Finance", new HashSet<>());

        // Asserting that the toString method is generated correctly by Lombok
        String expectedToString = "Department(id=1, name=Finance, employees=[])";
        assertEquals(expectedToString, department.toString(), "Expected toString representation doesn't match");
    }

    @Test
    public void testDepartmentEqualsAndHashCode() {
        // Creating two departments with the same data
        Department department1 = new Department(1L, "Finance", new HashSet<>());
        Department department2 = new Department(1L, "Finance", new HashSet<>());

        // Asserting that Lombok-generated equals and hashCode methods work as expected
        assertEquals(department1, department2, "Expected departments to be equal");
        assertEquals(department1.hashCode(), department2.hashCode(), "Expected hash codes to be equal");
    }

    @Test
    public void testDepartmentNoArgsConstructor() {
        // Using the no-args constructor (generated by Lombok)
        Department department = new Department();

        // Asserting that the default constructor initializes fields to null/empty
        assertNull(department.getId(), "Expected id to be null");
        assertNull(department.getName(), "Expected name to be null");
        assertNull(department.getEmployees(), "Expected employees to be null");
    }
}