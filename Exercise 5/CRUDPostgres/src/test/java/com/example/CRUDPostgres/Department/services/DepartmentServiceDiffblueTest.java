package com.example.CRUDPostgres.Department.services;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.example.CRUDPostgres.Department.dto.DepartmentDTO;
import com.example.CRUDPostgres.Department.entity.Department;
import com.example.CRUDPostgres.Department.exceptions.DepartmentNotFoundException;
import com.example.CRUDPostgres.Department.exceptions.DepartmentReferencedException;
import com.example.CRUDPostgres.Department.exceptions.DuplicateDepartmentException;
import com.example.CRUDPostgres.Department.mapper.DepartmentMapper;
import com.example.CRUDPostgres.Department.repository.DepartmentRepository;

import java.util.ArrayList;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.aot.DisabledInAotMode;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {DepartmentService.class})
@ExtendWith(SpringExtension.class)
@DisabledInAotMode
class DepartmentServiceDiffblueTest {
    @MockBean
    private DepartmentMapper departmentMapper;

    @MockBean
    private DepartmentRepository departmentRepository;

    @Autowired
    private DepartmentService departmentService;

    /**
     * Method under test: {@link DepartmentService#createDepartment(DepartmentDTO)}
     */
    @Test
    void testCreateDepartment() {
        // Arrange
        Department department = new Department();
        department.setEmployees(new HashSet<>());
        department.setId(1L);
        department.setName("Name");
        when(departmentRepository.findByName(Mockito.<String>any())).thenReturn(department);

        DepartmentDTO departmentDTO = new DepartmentDTO();
        departmentDTO.setId(1L);
        departmentDTO.setName("Name");

        // Act and Assert
        assertThrows(DuplicateDepartmentException.class, () -> departmentService.createDepartment(departmentDTO));
        verify(departmentRepository).findByName(eq("Name"));
    }

    /**
     * Method under test: {@link DepartmentService#createDepartment(DepartmentDTO)}
     */
    @Test
    void testCreateDepartment2() {
        // Arrange
        when(departmentRepository.findByName(Mockito.<String>any()))
                .thenThrow(new DepartmentReferencedException("An error occurred"));

        DepartmentDTO departmentDTO = new DepartmentDTO();
        departmentDTO.setId(1L);
        departmentDTO.setName("Name");

        // Act and Assert
        assertThrows(DepartmentReferencedException.class, () -> departmentService.createDepartment(departmentDTO));
        verify(departmentRepository).findByName(eq("Name"));
    }

    /**
     * Method under test: {@link DepartmentService#getAllDepartments()}
     */
    @Test
    void testGetAllDepartments() {
        // Arrange
        when(departmentRepository.findAll()).thenReturn(new ArrayList<>());

        // Act
        List<DepartmentDTO> actualAllDepartments = departmentService.getAllDepartments();

        // Assert
        verify(departmentRepository).findAll();
        assertTrue(actualAllDepartments.isEmpty());
    }

    /**
     * Method under test: {@link DepartmentService#getAllDepartments()}
     */
    @Test
    void testGetAllDepartments2() {
        // Arrange
        when(departmentMapper.toDTO(Mockito.<Department>any()))
                .thenThrow(new DuplicateDepartmentException("An error occurred"));

        Department department = new Department();
        department.setEmployees(new HashSet<>());
        department.setId(1L);
        department.setName("Name");

        ArrayList<Department> departmentList = new ArrayList<>();
        departmentList.add(department);
        when(departmentRepository.findAll()).thenReturn(departmentList);

        // Act and Assert
        assertThrows(DuplicateDepartmentException.class, () -> departmentService.getAllDepartments());
        verify(departmentMapper).toDTO(isA(Department.class));
        verify(departmentRepository).findAll();
    }

    /**
     * Method under test: {@link DepartmentService#getDepartmentById(Long)}
     */
    @Test
    void testGetDepartmentById() {
        // Arrange
        DepartmentDTO departmentDTO = new DepartmentDTO();
        departmentDTO.setId(1L);
        departmentDTO.setName("Name");
        when(departmentMapper.toDTO(Mockito.<Department>any())).thenReturn(departmentDTO);

        Department department = new Department();
        department.setEmployees(new HashSet<>());
        department.setId(1L);
        department.setName("Name");
        Optional<Department> ofResult = Optional.of(department);
        when(departmentRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);

        // Act
        DepartmentDTO actualDepartmentById = departmentService.getDepartmentById(1L);

        // Assert
        verify(departmentMapper).toDTO(isA(Department.class));
        verify(departmentRepository).findById(eq(1L));
        assertSame(departmentDTO, actualDepartmentById);
    }

    /**
     * Method under test: {@link DepartmentService#getDepartmentById(Long)}
     */
    @Test
    void testGetDepartmentById2() {
        // Arrange
        when(departmentMapper.toDTO(Mockito.<Department>any()))
                .thenThrow(new DuplicateDepartmentException("An error occurred"));

        Department department = new Department();
        department.setEmployees(new HashSet<>());
        department.setId(1L);
        department.setName("Name");
        Optional<Department> ofResult = Optional.of(department);
        when(departmentRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);

        // Act and Assert
        assertThrows(DuplicateDepartmentException.class, () -> departmentService.getDepartmentById(1L));
        verify(departmentMapper).toDTO(isA(Department.class));
        verify(departmentRepository).findById(eq(1L));
    }

    /**
     * Method under test: {@link DepartmentService#getDepartmentById(Long)}
     */
    @Test
    void testGetDepartmentById3() {
        // Arrange
        Optional<Department> emptyResult = Optional.empty();
        when(departmentRepository.findById(Mockito.<Long>any())).thenReturn(emptyResult);

        // Act and Assert
        assertThrows(DepartmentNotFoundException.class, () -> departmentService.getDepartmentById(1L));
        verify(departmentRepository).findById(eq(1L));
    }

    /**
     * Method under test: {@link DepartmentService#deleteDepartment(Long)}
     */
    @Test
    void testDeleteDepartment() {
        // Arrange
        doNothing().when(departmentRepository).deleteById(Mockito.<Long>any());
        when(departmentRepository.existsById(Mockito.<Long>any())).thenReturn(true);

        // Act
        departmentService.deleteDepartment(1L);

        // Assert that nothing has changed
        verify(departmentRepository).deleteById(eq(1L));
        verify(departmentRepository).existsById(eq(1L));
    }

    /**
     * Method under test: {@link DepartmentService#deleteDepartment(Long)}
     */
    @Test
    void testDeleteDepartment2() {
        // Arrange
        doThrow(new DuplicateDepartmentException("An error occurred")).when(departmentRepository)
                .deleteById(Mockito.<Long>any());
        when(departmentRepository.existsById(Mockito.<Long>any())).thenReturn(true);

        // Act and Assert
        assertThrows(DuplicateDepartmentException.class, () -> departmentService.deleteDepartment(1L));
        verify(departmentRepository).deleteById(eq(1L));
        verify(departmentRepository).existsById(eq(1L));
    }

    /**
     * Method under test: {@link DepartmentService#deleteDepartment(Long)}
     */
    @Test
    void testDeleteDepartment3() {
        // Arrange
        doThrow(new DataIntegrityViolationException("Msg")).when(departmentRepository).deleteById(Mockito.<Long>any());
        when(departmentRepository.existsById(Mockito.<Long>any())).thenReturn(true);

        // Act and Assert
        assertThrows(DepartmentReferencedException.class, () -> departmentService.deleteDepartment(1L));
        verify(departmentRepository).deleteById(eq(1L));
        verify(departmentRepository).existsById(eq(1L));
    }

    /**
     * Method under test: {@link DepartmentService#deleteDepartment(Long)}
     */
    @Test
    void testDeleteDepartment4() {
        // Arrange
        when(departmentRepository.existsById(Mockito.<Long>any())).thenReturn(false);

        // Act and Assert
        assertThrows(DepartmentNotFoundException.class, () -> departmentService.deleteDepartment(1L));
        verify(departmentRepository).existsById(eq(1L));
    }

    /**
     * Method under test: {@link DepartmentService#getDepartmentEntityById(Long)}
     */
    @Test
    void testGetDepartmentEntityById() {
        // Arrange
        Department department = new Department();
        department.setEmployees(new HashSet<>());
        department.setId(1L);
        department.setName("Name");
        Optional<Department> ofResult = Optional.of(department);
        when(departmentRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);

        // Act
        Department actualDepartmentEntityById = departmentService.getDepartmentEntityById(1L);

        // Assert
        verify(departmentRepository).findById(eq(1L));
        assertSame(department, actualDepartmentEntityById);
    }

    /**
     * Method under test: {@link DepartmentService#getDepartmentEntityById(Long)}
     */
    @Test
    void testGetDepartmentEntityById2() {
        // Arrange
        Optional<Department> emptyResult = Optional.empty();
        when(departmentRepository.findById(Mockito.<Long>any())).thenReturn(emptyResult);

        // Act and Assert
        assertThrows(DepartmentNotFoundException.class, () -> departmentService.getDepartmentEntityById(1L));
        verify(departmentRepository).findById(eq(1L));
    }

    /**
     * Method under test: {@link DepartmentService#getDepartmentEntityById(Long)}
     */
    @Test
    void testGetDepartmentEntityById3() {
        // Arrange
        when(departmentRepository.findById(Mockito.<Long>any()))
                .thenThrow(new DuplicateDepartmentException("An error occurred"));

        // Act and Assert
        assertThrows(DuplicateDepartmentException.class, () -> departmentService.getDepartmentEntityById(1L));
        verify(departmentRepository).findById(eq(1L));
    }
}
