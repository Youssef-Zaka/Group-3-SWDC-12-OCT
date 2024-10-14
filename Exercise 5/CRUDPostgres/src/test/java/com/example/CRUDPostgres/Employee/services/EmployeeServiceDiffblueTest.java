package com.example.CRUDPostgres.Employee.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.ArgumentMatchers.isNull;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.example.CRUDPostgres.Department.entity.Department;
import com.example.CRUDPostgres.Department.exceptions.DepartmentNotFoundException;
import com.example.CRUDPostgres.Department.services.DepartmentService;
import com.example.CRUDPostgres.Employee.dto.EmployeeDTO;
import com.example.CRUDPostgres.Employee.entity.Employee;
import com.example.CRUDPostgres.Employee.exceptions.EmployeeNotFoundException;
import com.example.CRUDPostgres.Employee.mapper.EmployeeMapper;
import com.example.CRUDPostgres.Employee.repository.EmployeeRepository;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.aot.DisabledInAotMode;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {EmployeeService.class})
@ExtendWith(SpringExtension.class)
@DisabledInAotMode
class EmployeeServiceDiffblueTest {
    @MockBean
    private DepartmentService departmentService;

    @MockBean
    private EmployeeMapper employeeMapper;

    @MockBean
    private EmployeeRepository employeeRepository;

    @Autowired
    private EmployeeService employeeService;

    /**
     * Method under test: {@link EmployeeService#saveEmployee(EmployeeDTO)}
     */
    @Test
    void testSaveEmployee() {
        // Arrange
        Department department = new Department();
        department.setEmployees(new HashSet<>());
        department.setId(1L);
        department.setName("Name");
        when(departmentService.getDepartmentEntityById(Mockito.<Long>any())).thenReturn(department);

        EmployeeDTO employeeDTO = new EmployeeDTO();
        employeeDTO.setDepartmentId(1L);
        employeeDTO.setId(1L);
        employeeDTO.setName("Name");

        Department department2 = new Department();
        department2.setEmployees(new HashSet<>());
        department2.setId(1L);
        department2.setName("Name");

        Employee employee = new Employee();
        employee.setDepartment(department2);
        employee.setId(1L);
        employee.setName("Name");
        when(employeeMapper.toDTO(Mockito.<Employee>any())).thenReturn(employeeDTO);
        when(employeeMapper.toEntity(Mockito.<EmployeeDTO>any(), Mockito.<Department>any())).thenReturn(employee);

        Department department3 = new Department();
        department3.setEmployees(new HashSet<>());
        department3.setId(1L);
        department3.setName("Name");

        Employee employee2 = new Employee();
        employee2.setDepartment(department3);
        employee2.setId(1L);
        employee2.setName("Name");
        when(employeeRepository.save(Mockito.<Employee>any())).thenReturn(employee2);

        EmployeeDTO employeeDTO2 = new EmployeeDTO();
        employeeDTO2.setDepartmentId(1L);
        employeeDTO2.setId(1L);
        employeeDTO2.setName("Name");

        // Act
        EmployeeDTO actualSaveEmployeeResult = employeeService.saveEmployee(employeeDTO2);

        // Assert
        verify(departmentService).getDepartmentEntityById(eq(1L));
        verify(employeeMapper).toDTO(isA(Employee.class));
        verify(employeeMapper).toEntity(isA(EmployeeDTO.class), isA(Department.class));
        verify(employeeRepository).save(isA(Employee.class));
        assertSame(employeeDTO, actualSaveEmployeeResult);
    }

    /**
     * Method under test: {@link EmployeeService#saveEmployee(EmployeeDTO)}
     */
    @Test
    void testSaveEmployee2() {
        // Arrange
        Department department = new Department();
        department.setEmployees(new HashSet<>());
        department.setId(1L);
        department.setName("Name");
        when(departmentService.getDepartmentEntityById(Mockito.<Long>any())).thenReturn(department);

        Department department2 = new Department();
        department2.setEmployees(new HashSet<>());
        department2.setId(1L);
        department2.setName("Name");

        Employee employee = new Employee();
        employee.setDepartment(department2);
        employee.setId(1L);
        employee.setName("Name");
        when(employeeMapper.toEntity(Mockito.<EmployeeDTO>any(), Mockito.<Department>any())).thenReturn(employee);
        when(employeeRepository.save(Mockito.<Employee>any()))
                .thenThrow(new DepartmentNotFoundException("An error occurred"));

        EmployeeDTO employeeDTO = new EmployeeDTO();
        employeeDTO.setDepartmentId(1L);
        employeeDTO.setId(1L);
        employeeDTO.setName("Name");

        // Act and Assert
        assertThrows(DepartmentNotFoundException.class, () -> employeeService.saveEmployee(employeeDTO));
        verify(departmentService).getDepartmentEntityById(eq(1L));
        verify(employeeMapper).toEntity(isA(EmployeeDTO.class), isA(Department.class));
        verify(employeeRepository).save(isA(Employee.class));
    }

    /**
     * Method under test: {@link EmployeeService#saveEmployee(EmployeeDTO)}
     */
    @Test
    void testSaveEmployee3() {
        // Arrange
        EmployeeDTO employeeDTO = new EmployeeDTO();
        employeeDTO.setDepartmentId(null);
        employeeDTO.setId(1L);
        employeeDTO.setName("Name");

        // Act and Assert
        assertThrows(DepartmentNotFoundException.class, () -> employeeService.saveEmployee(employeeDTO));
    }

    /**
     * Method under test: {@link EmployeeService#getAllEmployees(Pageable)}
     */
    @Test
    void testGetAllEmployees() {
        // Arrange
        PageImpl<Employee> pageImpl = new PageImpl<>(new ArrayList<>());
        when(employeeRepository.findAll(Mockito.<Pageable>any())).thenReturn(pageImpl);

        // Act
        Page<EmployeeDTO> actualAllEmployees = employeeService.getAllEmployees(null);

        // Assert
        verify(employeeRepository).findAll((Pageable) isNull());
        assertTrue(actualAllEmployees instanceof PageImpl);
        assertEquals(pageImpl, actualAllEmployees);
    }

    /**
     * Method under test: {@link EmployeeService#getAllEmployees(Pageable)}
     */
    @Test
    void testGetAllEmployees2() {
        // Arrange
        when(employeeMapper.toDTO(Mockito.<Employee>any())).thenThrow(new DepartmentNotFoundException("An error occurred"));

        Department department = new Department();
        department.setEmployees(new HashSet<>());
        department.setId(1L);
        department.setName("Name");

        Employee employee = new Employee();
        employee.setDepartment(department);
        employee.setId(1L);
        employee.setName("Name");

        ArrayList<Employee> content = new ArrayList<>();
        content.add(employee);
        PageImpl<Employee> pageImpl = new PageImpl<>(content);
        when(employeeRepository.findAll(Mockito.<Pageable>any())).thenReturn(pageImpl);

        // Act and Assert
        assertThrows(DepartmentNotFoundException.class, () -> employeeService.getAllEmployees(null));
        verify(employeeMapper).toDTO(isA(Employee.class));
        verify(employeeRepository).findAll((Pageable) isNull());
    }

    /**
     * Method under test: {@link EmployeeService#getEmployeeById(Long)}
     */
    @Test
    void testGetEmployeeById() {
        // Arrange
        EmployeeDTO employeeDTO = new EmployeeDTO();
        employeeDTO.setDepartmentId(1L);
        employeeDTO.setId(1L);
        employeeDTO.setName("Name");
        when(employeeMapper.toDTO(Mockito.<Employee>any())).thenReturn(employeeDTO);

        Department department = new Department();
        department.setEmployees(new HashSet<>());
        department.setId(1L);
        department.setName("Name");

        Employee employee = new Employee();
        employee.setDepartment(department);
        employee.setId(1L);
        employee.setName("Name");
        Optional<Employee> ofResult = Optional.of(employee);
        when(employeeRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);

        // Act
        EmployeeDTO actualEmployeeById = employeeService.getEmployeeById(1L);

        // Assert
        verify(employeeMapper).toDTO(isA(Employee.class));
        verify(employeeRepository).findById(eq(1L));
        assertSame(employeeDTO, actualEmployeeById);
    }

    /**
     * Method under test: {@link EmployeeService#getEmployeeById(Long)}
     */
    @Test
    void testGetEmployeeById2() {
        // Arrange
        when(employeeMapper.toDTO(Mockito.<Employee>any())).thenThrow(new DepartmentNotFoundException("An error occurred"));

        Department department = new Department();
        department.setEmployees(new HashSet<>());
        department.setId(1L);
        department.setName("Name");

        Employee employee = new Employee();
        employee.setDepartment(department);
        employee.setId(1L);
        employee.setName("Name");
        Optional<Employee> ofResult = Optional.of(employee);
        when(employeeRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);

        // Act and Assert
        assertThrows(DepartmentNotFoundException.class, () -> employeeService.getEmployeeById(1L));
        verify(employeeMapper).toDTO(isA(Employee.class));
        verify(employeeRepository).findById(eq(1L));
    }

    /**
     * Method under test: {@link EmployeeService#getEmployeeById(Long)}
     */
    @Test
    void testGetEmployeeById3() {
        // Arrange
        Optional<Employee> emptyResult = Optional.empty();
        when(employeeRepository.findById(Mockito.<Long>any())).thenReturn(emptyResult);

        // Act and Assert
        assertThrows(EmployeeNotFoundException.class, () -> employeeService.getEmployeeById(1L));
        verify(employeeRepository).findById(eq(1L));
    }

    /**
     * Method under test: {@link EmployeeService#updateEmployee(Long, EmployeeDTO)}
     */
    @Test
    void testUpdateEmployee() {
        // Arrange
        Department department = new Department();
        department.setEmployees(new HashSet<>());
        department.setId(1L);
        department.setName("Name");
        when(departmentService.getDepartmentEntityById(Mockito.<Long>any())).thenReturn(department);

        EmployeeDTO employeeDTO = new EmployeeDTO();
        employeeDTO.setDepartmentId(1L);
        employeeDTO.setId(1L);
        employeeDTO.setName("Name");
        when(employeeMapper.toDTO(Mockito.<Employee>any())).thenReturn(employeeDTO);

        Department department2 = new Department();
        department2.setEmployees(new HashSet<>());
        department2.setId(1L);
        department2.setName("Name");

        Employee employee = new Employee();
        employee.setDepartment(department2);
        employee.setId(1L);
        employee.setName("Name");
        Optional<Employee> ofResult = Optional.of(employee);

        Department department3 = new Department();
        department3.setEmployees(new HashSet<>());
        department3.setId(1L);
        department3.setName("Name");

        Employee employee2 = new Employee();
        employee2.setDepartment(department3);
        employee2.setId(1L);
        employee2.setName("Name");
        when(employeeRepository.save(Mockito.<Employee>any())).thenReturn(employee2);
        when(employeeRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);

        EmployeeDTO employeeDTO2 = new EmployeeDTO();
        employeeDTO2.setDepartmentId(1L);
        employeeDTO2.setId(1L);
        employeeDTO2.setName("Name");

        // Act
        EmployeeDTO actualUpdateEmployeeResult = employeeService.updateEmployee(1L, employeeDTO2);

        // Assert
        verify(departmentService).getDepartmentEntityById(eq(1L));
        verify(employeeMapper).toDTO(isA(Employee.class));
        verify(employeeRepository).findById(eq(1L));
        verify(employeeRepository).save(isA(Employee.class));
        assertSame(employeeDTO, actualUpdateEmployeeResult);
    }

    /**
     * Method under test: {@link EmployeeService#updateEmployee(Long, EmployeeDTO)}
     */
    @Test
    void testUpdateEmployee2() {
        // Arrange
        Department department = new Department();
        department.setEmployees(new HashSet<>());
        department.setId(1L);
        department.setName("Name");
        when(departmentService.getDepartmentEntityById(Mockito.<Long>any())).thenReturn(department);

        Department department2 = new Department();
        department2.setEmployees(new HashSet<>());
        department2.setId(1L);
        department2.setName("Name");

        Employee employee = new Employee();
        employee.setDepartment(department2);
        employee.setId(1L);
        employee.setName("Name");
        Optional<Employee> ofResult = Optional.of(employee);
        when(employeeRepository.save(Mockito.<Employee>any()))
                .thenThrow(new DepartmentNotFoundException("An error occurred"));
        when(employeeRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);

        EmployeeDTO employeeDTO = new EmployeeDTO();
        employeeDTO.setDepartmentId(1L);
        employeeDTO.setId(1L);
        employeeDTO.setName("Name");

        // Act and Assert
        assertThrows(DepartmentNotFoundException.class, () -> employeeService.updateEmployee(1L, employeeDTO));
        verify(departmentService).getDepartmentEntityById(eq(1L));
        verify(employeeRepository).findById(eq(1L));
        verify(employeeRepository).save(isA(Employee.class));
    }

    /**
     * Method under test: {@link EmployeeService#updateEmployee(Long, EmployeeDTO)}
     */
    @Test
    void testUpdateEmployee3() {
        // Arrange
        Optional<Employee> emptyResult = Optional.empty();
        when(employeeRepository.findById(Mockito.<Long>any())).thenReturn(emptyResult);

        EmployeeDTO employeeDTO = new EmployeeDTO();
        employeeDTO.setDepartmentId(1L);
        employeeDTO.setId(1L);
        employeeDTO.setName("Name");

        // Act and Assert
        assertThrows(EmployeeNotFoundException.class, () -> employeeService.updateEmployee(1L, employeeDTO));
        verify(employeeRepository).findById(eq(1L));
    }

    /**
     * Method under test: {@link EmployeeService#deleteEmployee(Long)}
     */
    @Test
    void testDeleteEmployee() {
        // Arrange
        doNothing().when(employeeRepository).deleteById(Mockito.<Long>any());
        when(employeeRepository.existsById(Mockito.<Long>any())).thenReturn(true);

        // Act
        employeeService.deleteEmployee(1L);

        // Assert that nothing has changed
        verify(employeeRepository).deleteById(eq(1L));
        verify(employeeRepository).existsById(eq(1L));
    }

    /**
     * Method under test: {@link EmployeeService#deleteEmployee(Long)}
     */
    @Test
    void testDeleteEmployee2() {
        // Arrange
        doThrow(new DepartmentNotFoundException("An error occurred")).when(employeeRepository)
                .deleteById(Mockito.<Long>any());
        when(employeeRepository.existsById(Mockito.<Long>any())).thenReturn(true);

        // Act and Assert
        assertThrows(DepartmentNotFoundException.class, () -> employeeService.deleteEmployee(1L));
        verify(employeeRepository).deleteById(eq(1L));
        verify(employeeRepository).existsById(eq(1L));
    }

    /**
     * Method under test: {@link EmployeeService#deleteEmployee(Long)}
     */
    @Test
    void testDeleteEmployee3() {
        // Arrange
        when(employeeRepository.existsById(Mockito.<Long>any())).thenReturn(false);

        // Act and Assert
        assertThrows(EmployeeNotFoundException.class, () -> employeeService.deleteEmployee(1L));
        verify(employeeRepository).existsById(eq(1L));
    }

    /**
     * Method under test: {@link EmployeeService#getEmployeesByDepartmentId(Long)}
     */
    @Test
    void testGetEmployeesByDepartmentId() {
        // Arrange
        Department department = new Department();
        department.setEmployees(new HashSet<>());
        department.setId(1L);
        department.setName("Name");
        when(departmentService.getDepartmentEntityById(Mockito.<Long>any())).thenReturn(department);
        when(employeeRepository.findByDepartmentId(Mockito.<Long>any())).thenReturn(new ArrayList<>());

        // Act
        List<EmployeeDTO> actualEmployeesByDepartmentId = employeeService.getEmployeesByDepartmentId(1L);

        // Assert
        verify(departmentService).getDepartmentEntityById(eq(1L));
        verify(employeeRepository).findByDepartmentId(eq(1L));
        assertTrue(actualEmployeesByDepartmentId.isEmpty());
    }

    /**
     * Method under test: {@link EmployeeService#getEmployeesByDepartmentId(Long)}
     */
    @Test
    void testGetEmployeesByDepartmentId2() {
        // Arrange
        Department department = new Department();
        department.setEmployees(new HashSet<>());
        department.setId(1L);
        department.setName("Name");
        when(departmentService.getDepartmentEntityById(Mockito.<Long>any())).thenReturn(department);
        when(employeeRepository.findByDepartmentId(Mockito.<Long>any()))
                .thenThrow(new DepartmentNotFoundException("An error occurred"));

        // Act and Assert
        assertThrows(DepartmentNotFoundException.class, () -> employeeService.getEmployeesByDepartmentId(1L));
        verify(departmentService).getDepartmentEntityById(eq(1L));
        verify(employeeRepository).findByDepartmentId(eq(1L));
    }

    /**
     * Method under test: {@link EmployeeService#getEmployeesByDepartmentId(Long)}
     */
    @Test
    void testGetEmployeesByDepartmentId3() {
        // Arrange
        Department department = new Department();
        department.setEmployees(new HashSet<>());
        department.setId(1L);
        department.setName("Name");
        when(departmentService.getDepartmentEntityById(Mockito.<Long>any())).thenReturn(department);
        when(employeeMapper.toDTO(Mockito.<Employee>any())).thenThrow(new DepartmentNotFoundException("An error occurred"));

        Department department2 = new Department();
        department2.setEmployees(new HashSet<>());
        department2.setId(1L);
        department2.setName("Name");

        Employee employee = new Employee();
        employee.setDepartment(department2);
        employee.setId(1L);
        employee.setName("Name");

        ArrayList<Employee> employeeList = new ArrayList<>();
        employeeList.add(employee);
        when(employeeRepository.findByDepartmentId(Mockito.<Long>any())).thenReturn(employeeList);

        // Act and Assert
        assertThrows(DepartmentNotFoundException.class, () -> employeeService.getEmployeesByDepartmentId(1L));
        verify(departmentService).getDepartmentEntityById(eq(1L));
        verify(employeeMapper).toDTO(isA(Employee.class));
        verify(employeeRepository).findByDepartmentId(eq(1L));
    }
}
