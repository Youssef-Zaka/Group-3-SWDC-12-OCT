package com.example.CRUDPostgres.Shared;

import com.example.CRUDPostgres.Department.exceptions.DepartmentNotFoundException;
import com.example.CRUDPostgres.Department.exceptions.DepartmentReferencedException;
import com.example.CRUDPostgres.Department.exceptions.DuplicateDepartmentException;
import com.example.CRUDPostgres.Employee.exceptions.EmployeeNotFoundException;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

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

    /**
     * Handle ConstraintViolationException, which occurs when validation constraints are violated.
     *
     * @param ex The ConstraintViolationException.
     * @return A ResponseEntity with a detailed error message and a status of 400 Bad Request.
     */
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<Map<String, String>> handleConstraintViolationException(ConstraintViolationException ex) {
        // Extract the validation error messages and format them into a map
        Map<String, String> errors = ex.getConstraintViolations()
                .stream()
                .collect(Collectors.toMap(
                        violation -> violation.getPropertyPath().toString(), // Field name
                        ConstraintViolation::getMessage // Error message
                ));

        // Return a response entity with the error details and HTTP status 400
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

    /**
     * Handle validation errors from the request body (e.g., EmployeeDTO).
     *
     * @param ex The MethodArgumentNotValidException.
     * @return A ResponseEntity with validation error details and a status of 400 Bad Request.
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();

        // Loop through the field errors and put them in the map
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField(); // Get the field that failed validation
            String errorMessage = error.getDefaultMessage(); // Get the error message
            errors.put(fieldName, errorMessage); // Add to the map
        });

        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

    /**
     * Handle DataIntegrityViolationException, which occurs when a foreign key constraint is violated.
     *
     * @param ex The DataIntegrityViolationException.
     * @return A ResponseEntity with a user-friendly error message and a status of 400 Bad Request.
     */
    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<Map<String, String>> handleDataIntegrityViolationException(DataIntegrityViolationException ex) {
        Map<String, String> errors = new HashMap<>();
        errors.put("error", "A data integrity violation occurred. Please ensure all required fields are valid.");

        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

    /**
     * Handle custom DepartmentReferencedException.
     *
     * @param ex The DepartmentReferencedException.
     * @return A ResponseEntity with a user-friendly error message and a status of 400 Bad Request.
     */
    @ExceptionHandler(DepartmentReferencedException.class)
    public ResponseEntity<Map<String, String>> handleDepartmentReferencedException(DepartmentReferencedException ex) {
        Map<String, String> errors = new HashMap<>();
        errors.put("error", ex.getMessage());

        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

}
