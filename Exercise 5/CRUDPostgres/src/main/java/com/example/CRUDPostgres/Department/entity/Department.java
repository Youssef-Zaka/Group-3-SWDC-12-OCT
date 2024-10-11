package com.example.CRUDPostgres.Department.entity;

import com.example.CRUDPostgres.Employee.entity.Employee;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.Set;

@Entity
@Table(name = "department", uniqueConstraints = @UniqueConstraint(columnNames = "name"))
@Data // Lombok for getters, setters, etc.
@NoArgsConstructor
@AllArgsConstructor
public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotBlank(message = "Department name cannot be blank")
    @Size(min = 2, message = "Name must be at least 2 characters long") // Validate length
    private String name;

    @OneToMany(mappedBy = "department", cascade = CascadeType.PERSIST) // One department can have many employees
    private Set<Employee> employees; // A set of employees belonging to this department
}
