package com.example.CRUDPostgres.Employee.dto;

import jakarta.validation.constraints.*;
import lombok.Data;

/**
 * Data Transfer Object for Employee entity.
 */
@Data // Lombok annotation for generating getters, setters, and other methods
public class EmployeeDTO {

    private Long id; // Employee ID

    @NotBlank(message = "Name cannot be blank") // Validation for the name
    @Size(min = 2, message = "Name must be at least 2 characters long") // Validate length
    private String name;

    @NotNull(message = "Department ID cannot be null") // Validation for Department ID
    @Min(value = 1, message = "Department ID must be greater than zero") // Minimum value for Department ID
    private Long departmentId;

}
