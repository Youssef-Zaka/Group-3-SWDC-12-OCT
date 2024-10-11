package com.example.CRUDPostgres.Shared;

import com.example.CRUDPostgres.Department.exceptions.DepartmentNotFoundException;
import com.example.CRUDPostgres.Department.exceptions.DuplicateDepartmentException;
import com.example.CRUDPostgres.Employee.exceptions.EmployeeNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * Global exception handler to handle application-wide exceptions.
 */
@ControllerAdvice
public class GlobalExceptionHandler {
    /**
     * Handle EmployeeNotFoundException and return a meaningful response.
     *
     * @param ex The EmployeeNotFoundException that was thrown.
     * @return A ResponseEntity with a status of 404 (Not Found) and the exception message.
     */
    @ExceptionHandler(EmployeeNotFoundException.class)
    public ResponseEntity<String> handleEmployeeNotFoundException(EmployeeNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage()); // 404 Not Found
    }


    /**
     * Handle DuplicateDepartmentException and return a meaningful response.
     *
     * @param ex The DuplicateDepartmentException that was thrown.
     * @return A ResponseEntity with a status of 409 (Conflict) and the exception message.
     */
    @ExceptionHandler(DuplicateDepartmentException.class)
    public ResponseEntity<String> handleDuplicateDepartmentException(DuplicateDepartmentException ex) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(ex.getMessage()); // 409 Conflict
    }

    /**
     * Handle DepartmentNotFoundException and return a meaningful response.
     *
     * @param ex The DepartmentNotFoundException that was thrown.
     * @return A ResponseEntity with a status of 404 (Not Found) and the exception message.
     */
    @ExceptionHandler(DepartmentNotFoundException.class)
    public ResponseEntity<String> handleDepartmentNotFoundException(DepartmentNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage()); // 404 Not Found
    }
}
