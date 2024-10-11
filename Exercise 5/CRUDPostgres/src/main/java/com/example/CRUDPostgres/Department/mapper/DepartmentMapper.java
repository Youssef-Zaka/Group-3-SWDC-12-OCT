package com.example.CRUDPostgres.Department.mapper;

import com.example.CRUDPostgres.Department.dto.DepartmentDTO;
import com.example.CRUDPostgres.Department.entity.Department;
import org.springframework.stereotype.Component;

@Component // This makes it a Spring-managed bean
public class DepartmentMapper {

    // Convert Department entity to DepartmentDTO
    public DepartmentDTO toDTO(Department department) {
        DepartmentDTO dto = new DepartmentDTO();
        dto.setId(department.getId());
        dto.setName(department.getName());
        return dto;
    }

    // Convert DepartmentDTO to Department entity
    public Department toEntity(DepartmentDTO dto) {
        Department department = new Department();
        department.setId(dto.getId());
        department.setName(dto.getName());
        return department;
    }
}
