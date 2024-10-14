package com.example.CRUDPostgres.Department.mapper;


import com.example.CRUDPostgres.Department.dto.DepartmentDTO;
import com.example.CRUDPostgres.Department.entity.Department;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DepartmentMapperTest {

    private DepartmentMapper departmentMapper;

    @BeforeEach
    void setUp() {
        // Initialize the mapper before each test
        departmentMapper = new DepartmentMapper();
    }

    @Test
    void testToDTO_withValidDepartment() {
        // Arrange
        Department department = new Department();
        department.setId(1L);
        department.setName("HR");

        // Act
        DepartmentDTO result = departmentMapper.toDTO(department);

        // Assert
        assertNotNull(result, "The result should not be null");
        assertEquals(1L, result.getId(), "The department ID should match");
        assertEquals("HR", result.getName(), "The department name should match");
    }

    @Test
    void testToDTO_withNullDepartment() {
        // Act & Assert
        assertThrows(NullPointerException.class, () -> departmentMapper.toDTO(null),
                "Converting a null Department should throw a NullPointerException");
    }

    @Test
    void testToEntity_withValidDTO() {
        // Arrange
        DepartmentDTO departmentDTO = new DepartmentDTO();
        departmentDTO.setId(1L);
        departmentDTO.setName("Engineering");

        // Act
        Department result = departmentMapper.toEntity(departmentDTO);

        // Assert
        assertNotNull(result, "The result should not be null");
        assertEquals(1L, result.getId(), "The department ID should match");
        assertEquals("Engineering", result.getName(), "The department name should match");
    }

    @Test
    void testToEntity_withDTOWithoutId() {
        // Arrange
        DepartmentDTO departmentDTO = new DepartmentDTO();
        departmentDTO.setName("Marketing");

        // Act
        Department result = departmentMapper.toEntity(departmentDTO);

        // Assert
        assertNotNull(result, "The result should not be null");
        assertNull(result.getId(), "The department ID should be null");
        assertEquals("Marketing", result.getName(), "The department name should match");
    }

    @Test
    void testToEntity_withNullDTO() {
        // Act & Assert
        assertThrows(NullPointerException.class, () -> departmentMapper.toEntity(null),
                "Converting a null DepartmentDTO should throw a NullPointerException");
    }
}