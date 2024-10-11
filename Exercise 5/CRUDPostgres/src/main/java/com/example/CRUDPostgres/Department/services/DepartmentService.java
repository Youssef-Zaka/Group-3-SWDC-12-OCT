package com.example.CRUDPostgres.Department.services;

import com.example.CRUDPostgres.Department.dto.DepartmentDTO;
import com.example.CRUDPostgres.Department.entity.Department;
import com.example.CRUDPostgres.Department.exceptions.DepartmentNotFoundException;
import com.example.CRUDPostgres.Department.exceptions.DuplicateDepartmentException;
import com.example.CRUDPostgres.Department.mapper.DepartmentMapper;
import com.example.CRUDPostgres.Department.repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Service class for managing Department entities.
 * Provides methods for CRUD operations on departments.
 */
@Service
public class DepartmentService {

    @Autowired
    private DepartmentRepository departmentRepository;

    @Autowired
    private DepartmentMapper departmentMapper;

    /**
     * Creates a new department and saves it to the database.
     *
     * @param departmentDTO The DTO representation of the department to be created.
     * @return The created department as a DTO.
     * @throws DuplicateDepartmentException if a department with the same name already exists.
     */
    public DepartmentDTO createDepartment(DepartmentDTO departmentDTO) {
        // Check if the department name already exists
        if (departmentRepository.findByName(departmentDTO.getName()) != null) {
            throw new DuplicateDepartmentException("Department with name " + departmentDTO.getName() + " already exists.");
        }
        Department department = departmentMapper.toEntity(departmentDTO); // Convert DTO to Entity
        department = departmentRepository.save(department); // Save entity
        return departmentMapper.toDTO(department); // Convert back to DTO to return
    }

    /**
     * Retrieves all departments from the database.
     *
     * @return A list of all departments as DTOs.
     */
    public List<DepartmentDTO> getAllDepartments() {
        return departmentRepository.findAll().stream()
                .map(departmentMapper::toDTO) // Convert each entity to DTO
                .collect(Collectors.toList());
    }

    /**
     * Retrieves a department by its ID.
     *
     * @param id The ID of the department to retrieve.
     * @return The department as a DTO if found, or null if not found.
     * @throws DepartmentNotFoundException if the department with the given ID does not exist.
     */
    public DepartmentDTO getDepartmentById(Long id) {
        return departmentRepository.findById(id)
                .map(departmentMapper::toDTO) // Convert entity to DTO if found
                .orElseThrow(() -> new DepartmentNotFoundException("Department with ID " + id + " not found."));
    }

    /**
     * Deletes a department by its ID.
     *
     * @param id The ID of the department to be deleted.
     * @throws DepartmentNotFoundException if the department with the given ID does not exist.
     */
    public void deleteDepartment(Long id) {
        if (!departmentRepository.existsById(id)) {
            throw new DepartmentNotFoundException("Department with ID " + id + " not found.");
        }
        departmentRepository.deleteById(id);
    }
}
