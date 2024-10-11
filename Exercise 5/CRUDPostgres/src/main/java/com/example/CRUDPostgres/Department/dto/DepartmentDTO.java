package com.example.CRUDPostgres.Department.dto;

import lombok.Data;
import jakarta.validation.constraints.NotBlank;

/**
 * Data Transfer Object for Department entity.
 */
@Data // Lombok annotation for generating getters, setters, etc.
public class DepartmentDTO {

    private Long id; // Department ID

    @NotBlank(message = "Department name cannot be blank") // Validation for department name
    private String name; // Department name

}
