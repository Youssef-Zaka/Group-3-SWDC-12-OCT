package com.example.CRUDPostgres.Employee.exceptions;

/**
 * Custom exception to be thrown when an employee is not found.
 */
public class EmployeeNotFoundException extends RuntimeException {
    public EmployeeNotFoundException(String message) {
        super(message);
    }
}
