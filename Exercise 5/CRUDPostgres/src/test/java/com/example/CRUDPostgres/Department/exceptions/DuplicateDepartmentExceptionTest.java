package com.example.CRUDPostgres.Department.exceptions;


import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class DuplicateDepartmentExceptionTest {

    @Test
    void testExceptionMessage() {
        String message = "Department already exists";
        DuplicateDepartmentException exception = new DuplicateDepartmentException(message);

        // Assert the message matches
        assertEquals(message, exception.getMessage());
    }

    @Test
    void testEmptyConstructor() {
        DuplicateDepartmentException exception = new DuplicateDepartmentException(null);
        assertNull(exception.getMessage()); // No message passed
    }
}