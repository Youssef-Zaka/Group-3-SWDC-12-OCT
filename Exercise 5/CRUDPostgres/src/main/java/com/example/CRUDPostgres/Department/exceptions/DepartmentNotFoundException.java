package com.example.CRUDPostgres.Department.exceptions;

/**
 * Custom exception to be thrown when a department is not found.
 */
public class DepartmentNotFoundException extends RuntimeException {
    public DepartmentNotFoundException(String message) {
        super(message);
    }
}
