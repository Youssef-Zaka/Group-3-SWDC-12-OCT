package com.example.CRUDPostgres.Department.exceptions;


import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class DepartmentReferencedExceptionTest {

    @Test
    void testExceptionMessage() {
        String message = "Cannot delete department because it is referenced by employees.";
        DepartmentReferencedException exception = new DepartmentReferencedException(message);

        // Assert the message matches
        assertEquals(message, exception.getMessage());
    }

    @Test
    void testEmptyConstructor() {
        DepartmentReferencedException exception = new DepartmentReferencedException(null);
        assertNull(exception.getMessage()); // No message passed
    }
}