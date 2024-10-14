package com.example.CRUDPostgres.Department.exceptions;

public class DepartmentReferencedException extends RuntimeException {

    public DepartmentReferencedException(String message) {
        super(message);
    }
}