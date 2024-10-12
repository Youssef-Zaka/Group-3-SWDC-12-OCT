package com.example.CRUDPostgres.Employee.controller;


import com.example.CRUDPostgres.Employee.dto.EmployeeDTO;
import com.example.CRUDPostgres.Employee.services.EmployeeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class EmployeeControllerTest {

    @Mock
    private EmployeeService employeeService;

    @InjectMocks
    private EmployeeController employeeController;

    private EmployeeDTO employeeDTO;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        employeeDTO = new EmployeeDTO();
        employeeDTO.setId(1L);
        employeeDTO.setName("John Doe");
        employeeDTO.setDepartmentId(1L);
    }

    @Test
    void testAddEmployee() {
        // Arrange
        when(employeeService.saveEmployee(any(EmployeeDTO.class))).thenReturn(employeeDTO);

        // Act
        ResponseEntity<EmployeeDTO> response = employeeController.addEmployee(employeeDTO);

        // Assert
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(employeeDTO.getName(), response.getBody().getName());
        verify(employeeService, times(1)).saveEmployee(any(EmployeeDTO.class));
    }

    @Test
    void testGetAllEmployees() {
        // Arrange
        Pageable pageable = PageRequest.of(0, 10);
        Page<EmployeeDTO> page = new PageImpl<>(Collections.singletonList(employeeDTO));
        when(employeeService.getAllEmployees(pageable)).thenReturn(page);

        // Act
        ResponseEntity<Page<EmployeeDTO>> response = employeeController.getAllEmployees(pageable);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(1, response.getBody().getContent().size());
        verify(employeeService, times(1)).getAllEmployees(pageable);
    }

    @Test
    void testGetEmployeeById() {
        // Arrange
        when(employeeService.getEmployeeById(1L)).thenReturn(employeeDTO);

        // Act
        ResponseEntity<EmployeeDTO> response = employeeController.getEmployeeById(1L);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(1L, response.getBody().getId());
        verify(employeeService, times(1)).getEmployeeById(1L);
    }

    @Test
    void testUpdateEmployee() {
        // Arrange
        when(employeeService.updateEmployee(eq(1L), any(EmployeeDTO.class))).thenReturn(employeeDTO);

        // Act
        ResponseEntity<EmployeeDTO> response = employeeController.updateEmployee(1L, employeeDTO);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(employeeDTO.getName(), response.getBody().getName());
        verify(employeeService, times(1)).updateEmployee(eq(1L), any(EmployeeDTO.class));
    }

    @Test
    void testDeleteEmployee() {
        // Act
        ResponseEntity<Void> response = employeeController.deleteEmployee(1L);

        // Assert
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(employeeService, times(1)).deleteEmployee(1L);
    }

    @Test
    void testGetEmployeesByDepartmentId() {
        // Arrange
        List<EmployeeDTO> employeeList = Collections.singletonList(employeeDTO);
        when(employeeService.getEmployeesByDepartmentId(1L)).thenReturn(employeeList);

        // Act
        ResponseEntity<List<EmployeeDTO>> response = employeeController.getEmployeesByDepartmentId(1L);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(1, response.getBody().size());
        verify(employeeService, times(1)).getEmployeesByDepartmentId(1L);
    }
}