package com.example.CRUDPostgres.Employee.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.util.Set;

@Entity
@Table(name = "department")
@Data // Lombok for getters, setters, etc.
@NoArgsConstructor
@AllArgsConstructor
public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotBlank(message = "Department name cannot be blank")
    private String name;

    @OneToMany(mappedBy = "department", cascade = CascadeType.PERSIST) // One department can have many employees
    private Set<Employee> employees; // A set of employees belonging to this department
}
