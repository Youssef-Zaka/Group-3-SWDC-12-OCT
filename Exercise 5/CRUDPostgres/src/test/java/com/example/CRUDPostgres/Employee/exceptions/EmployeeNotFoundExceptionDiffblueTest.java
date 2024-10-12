package com.example.CRUDPostgres.Employee.exceptions;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;

class EmployeeNotFoundExceptionDiffblueTest {
    /**
     * Method under test:
     * {@link EmployeeNotFoundException#EmployeeNotFoundException(String)}
     */
    @Test
    void testNewEmployeeNotFoundException() {
        // Arrange and Act
        EmployeeNotFoundException actualEmployeeNotFoundException = new EmployeeNotFoundException("An error occurred");

        // Assert
        assertEquals("An error occurred", actualEmployeeNotFoundException.getMessage());
        assertNull(actualEmployeeNotFoundException.getCause());
        assertEquals(0, actualEmployeeNotFoundException.getSuppressed().length);
    }
}
