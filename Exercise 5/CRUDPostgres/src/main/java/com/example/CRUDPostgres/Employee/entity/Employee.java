package com.example.CRUDPostgres.Employee.entity;

import com.example.CRUDPostgres.Department.entity.Department;
import com.example.CRUDPostgres.Shared.Auditable;
import jakarta.persistence.*;
import lombok.*; // Import Lombok annotations
import jakarta.validation.constraints.*; // Import validation annotations
/**
 * Represents an employee entity in the database.
 */
@Entity
@Table(name = "employee")
@Data // Lombok annotation for getters, setters, toString, equals, and hashCode
@EqualsAndHashCode(callSuper = false) // Lombok annotation to generate equals and hashCode methods
@NoArgsConstructor // Lombok annotation for no-args constructor
@AllArgsConstructor // Lombok annotation for all-args constructor
public class Employee extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Name cannot be blank")
    @Size(min = 2, message = "Name must be at least 2 characters long") // Validate length
    private String name;

    @NotNull(message = "Department cannot be null")
    @ManyToOne // Many employees belong to one department
    @JoinColumn(name = "department_id", nullable = false) // Foreign key column in employee table
    private Department department; // The department this employee belongs to

}