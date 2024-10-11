package com.example.CRUDPostgres.Employee.entity;

import jakarta.persistence.*;
import lombok.*; // Import Lombok annotations
import jakarta.validation.constraints.*; // Import validation annotations
/**
 * Represents an employee entity in the database.
 */
@Entity
@Table(name = "employee")
@Data // Lombok annotation for getters, setters, toString, equals, and hashCode
@NoArgsConstructor // Lombok annotation for no-args constructor
@AllArgsConstructor // Lombok annotation for all-args constructor
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotBlank(message = "Name cannot be blank")
    private String name;

    @NotNull(message = "Department cannot be null")
    @ManyToOne // Many employees belong to one department
    @JoinColumn(name = "department_id") // Foreign key column in employee table
    private Department department; // The department this employee belongs to

}