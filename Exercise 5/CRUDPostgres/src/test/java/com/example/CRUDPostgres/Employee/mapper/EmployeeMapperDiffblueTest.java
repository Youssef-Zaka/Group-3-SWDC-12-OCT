package com.example.CRUDPostgres.Employee.mapper;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.anyLong;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.example.CRUDPostgres.Department.entity.Department;
import com.example.CRUDPostgres.Department.mapper.DepartmentMapper;
import com.example.CRUDPostgres.Employee.dto.EmployeeDTO;
import com.example.CRUDPostgres.Employee.entity.Employee;

import java.util.HashSet;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.aot.DisabledInAotMode;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {EmployeeMapper.class})
@ExtendWith(SpringExtension.class)
@DisabledInAotMode
class EmployeeMapperDiffblueTest {
    @MockBean
    private DepartmentMapper departmentMapper;

    @Autowired
    private EmployeeMapper employeeMapper;

    /**
     * Method under test: {@link EmployeeMapper#toDTO(Employee)}
     */
    @Test
    void testToDTO() {
        // Arrange
        Department department = new Department();
        department.setEmployees(new HashSet<>());
        department.setId(1L);
        department.setName("Name");

        Employee employee = new Employee();
        employee.setDepartment(department);
        employee.setId(1L);
        employee.setName("Name");

        // Act
        EmployeeDTO actualToDTOResult = employeeMapper.toDTO(employee);

        // Assert
        assertEquals("Name", actualToDTOResult.getName());
        assertEquals(1L, actualToDTOResult.getDepartmentId().longValue());
        assertEquals(1L, actualToDTOResult.getId().longValue());
    }

    /**
     * Method under test: {@link EmployeeMapper#toDTO(Employee)}
     */
    @Test
    void testToDTO2() {
        // Arrange
        Department department = new Department();
        department.setEmployees(new HashSet<>());
        department.setId(1L);
        department.setName("Name");

        Department department2 = new Department();
        department2.setEmployees(new HashSet<>());
        department2.setId(1L);
        department2.setName("Name");
        Employee employee = mock(Employee.class);
        when(employee.getDepartment()).thenReturn(department2);
        when(employee.getName()).thenReturn("Name");
        when(employee.getId()).thenReturn(1L);
        doNothing().when(employee).setDepartment(Mockito.<Department>any());
        doNothing().when(employee).setId(anyLong());
        doNothing().when(employee).setName(Mockito.<String>any());
        employee.setDepartment(department);
        employee.setId(1L);
        employee.setName("Name");

        // Act
        EmployeeDTO actualToDTOResult = employeeMapper.toDTO(employee);

        // Assert
        verify(employee, atLeast(1)).getDepartment();
        verify(employee).getId();
        verify(employee).getName();
        verify(employee).setDepartment(isA(Department.class));
        verify(employee).setId(eq(1L));
        verify(employee).setName(eq("Name"));
        assertEquals("Name", actualToDTOResult.getName());
        assertEquals(1L, actualToDTOResult.getDepartmentId().longValue());
        assertEquals(1L, actualToDTOResult.getId().longValue());
    }

    /**
     * Method under test: {@link EmployeeMapper#toEntity(EmployeeDTO, Department)}
     */
    @Test
    void testToEntity() {
        // Arrange
        EmployeeDTO dto = new EmployeeDTO();
        dto.setDepartmentId(1L);
        dto.setId(1L);
        dto.setName("Name");

        Department department = new Department();
        department.setEmployees(new HashSet<>());
        department.setId(1L);
        department.setName("Name");

        // Act
        Employee actualToEntityResult = employeeMapper.toEntity(dto, department);

        // Assert
        assertEquals("Name", actualToEntityResult.getName());
        assertEquals(1L, actualToEntityResult.getId());
        assertSame(department, actualToEntityResult.getDepartment());
    }

    /**
     * Method under test: {@link EmployeeMapper#toEntity(EmployeeDTO, Department)}
     */
    @Test
    void testToEntity2() {
        // Arrange
        EmployeeDTO dto = mock(EmployeeDTO.class);
        when(dto.getId()).thenReturn(1L);
        when(dto.getName()).thenReturn("Name");
        doNothing().when(dto).setDepartmentId(Mockito.<Long>any());
        doNothing().when(dto).setId(Mockito.<Long>any());
        doNothing().when(dto).setName(Mockito.<String>any());
        dto.setDepartmentId(1L);
        dto.setId(1L);
        dto.setName("Name");

        Department department = new Department();
        department.setEmployees(new HashSet<>());
        department.setId(1L);
        department.setName("Name");

        // Act
        Employee actualToEntityResult = employeeMapper.toEntity(dto, department);

        // Assert
        verify(dto, atLeast(1)).getId();
        verify(dto).getName();
        verify(dto).setDepartmentId(eq(1L));
        verify(dto).setId(eq(1L));
        verify(dto).setName(eq("Name"));
        assertEquals("Name", actualToEntityResult.getName());
        assertEquals(1L, actualToEntityResult.getId());
        assertSame(department, actualToEntityResult.getDepartment());
    }

    /**
     * Method under test: {@link EmployeeMapper#toEntity(EmployeeDTO, Department)}
     */
    @Test
    void testToEntity3() {
        // Arrange
        EmployeeDTO dto = mock(EmployeeDTO.class);
        when(dto.getId()).thenReturn(null);
        when(dto.getName()).thenReturn("Name");
        doNothing().when(dto).setDepartmentId(Mockito.<Long>any());
        doNothing().when(dto).setId(Mockito.<Long>any());
        doNothing().when(dto).setName(Mockito.<String>any());
        dto.setDepartmentId(1L);
        dto.setId(1L);
        dto.setName("Name");

        Department department = new Department();
        department.setEmployees(new HashSet<>());
        department.setId(1L);
        department.setName("Name");

        // Act
        Employee actualToEntityResult = employeeMapper.toEntity(dto, department);

        // Assert
        verify(dto).getId();
        verify(dto).getName();
        verify(dto).setDepartmentId(eq(1L));
        verify(dto).setId(eq(1L));
        verify(dto).setName(eq("Name"));
        assertEquals("Name", actualToEntityResult.getName());
        assertNull( actualToEntityResult.getId());
        assertSame(department, actualToEntityResult.getDepartment());
    }
}
