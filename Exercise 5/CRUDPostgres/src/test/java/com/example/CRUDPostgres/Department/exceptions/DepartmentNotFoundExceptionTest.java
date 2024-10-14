package com.example.CRUDPostgres.Department.exceptions;


import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class DepartmentNotFoundExceptionTest {

    @Test
    void testExceptionMessage() {
        String message = "Department with ID 1 not found";
        DepartmentNotFoundException exception = new DepartmentNotFoundException(message);

        // Assert the message matches
        assertEquals(message, exception.getMessage());
    }

    @Test
    void testEmptyConstructor() {
        DepartmentNotFoundException exception = new DepartmentNotFoundException(null);
        assertNull(exception.getMessage()); // No message passed
    }
}