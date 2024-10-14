package com.example.CRUDPostgres.Employee.entity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.anyLong;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import com.example.CRUDPostgres.Department.entity.Department;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {Employee.class})
@ExtendWith(SpringExtension.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
class EmployeeDiffblueTest {
    @Autowired
    private Employee employee;

    /**
     * Methods under test:
     * <ul>
     *   <li>{@link Employee#equals(Object)}
     *   <li>{@link Employee#hashCode()}
     * </ul>
     */
    @Test
    void testEqualsAndHashCode_whenOtherIsEqual_thenReturnEqual() {
        // Arrange
        Department department = new Department();
        department.setEmployees(new HashSet<>());
        department.setId(1L);
        department.setName("Name");

        Employee employee = new Employee();
        employee.setDepartment(department);
        employee.setId(1L);
        employee.setName("Name");

        Department department2 = new Department();
        department2.setEmployees(new HashSet<>());
        department2.setId(1L);
        department2.setName("Name");

        Employee employee2 = new Employee();
        employee2.setDepartment(department2);
        employee2.setId(1L);
        employee2.setName("Name");

        // Act and Assert
        assertEquals(employee, employee2);
        int expectedHashCodeResult = employee.hashCode();
        assertEquals(expectedHashCodeResult, employee2.hashCode());
    }

    /**
     * Methods under test:
     * <ul>
     *   <li>{@link Employee#equals(Object)}
     *   <li>{@link Employee#hashCode()}
     * </ul>
     */
    @Test
    void testEqualsAndHashCode_whenOtherIsSame_thenReturnEqual() {
        // Arrange
        Department department = new Department();
        department.setEmployees(new HashSet<>());
        department.setId(1L);
        department.setName("Name");

        Employee employee = new Employee();
        employee.setDepartment(department);
        employee.setId(1L);
        employee.setName("Name");

        // Act and Assert
        assertEquals(employee, employee);
        int expectedHashCodeResult = employee.hashCode();
        assertEquals(expectedHashCodeResult, employee.hashCode());
    }

    /**
     * Method under test: {@link Employee#equals(Object)}
     */
    @Test
    void testEquals_whenOtherIsDifferent_thenReturnNotEqual() {
        // Arrange
        Department department = mock(Department.class);
        doNothing().when(department).setEmployees(Mockito.<Set<Employee>>any());
        doNothing().when(department).setId(anyLong());
        doNothing().when(department).setName(Mockito.<String>any());
        department.setEmployees(new HashSet<>());
        department.setId(1L);
        department.setName("Name");

        Employee employee = new Employee();
        employee.setDepartment(department);
        employee.setId(1L);
        employee.setName("Name");

        Department department2 = new Department();
        department2.setEmployees(new HashSet<>());
        department2.setId(1L);
        department2.setName("Name");

        Employee employee2 = new Employee();
        employee2.setDepartment(department2);
        employee2.setId(1L);
        employee2.setName("Name");

        // Act and Assert
        assertNotEquals(employee, employee2);
    }

    /**
     * Method under test: {@link Employee#equals(Object)}
     */
    @Test
    void testEquals_whenOtherIsDifferent_thenReturnNotEqual2() {
        // Arrange
        Department department = mock(Department.class);
        doNothing().when(department).setEmployees(Mockito.<Set<Employee>>any());
        doNothing().when(department).setId(anyLong());
        doNothing().when(department).setName(Mockito.<String>any());
        department.setEmployees(new HashSet<>());
        department.setId(1L);
        department.setName("Name");

        Employee employee = new Employee();
        employee.setDepartment(department);
        employee.setId(2L);
        employee.setName("Name");

        Department department2 = new Department();
        department2.setEmployees(new HashSet<>());
        department2.setId(1L);
        department2.setName("Name");

        Employee employee2 = new Employee();
        employee2.setDepartment(department2);
        employee2.setId(1L);
        employee2.setName("Name");

        // Act and Assert
        assertNotEquals(employee, employee2);
    }

    /**
     * Method under test: {@link Employee#equals(Object)}
     */
    @Test
    void testEquals_whenOtherIsDifferent_thenReturnNotEqual3() {
        // Arrange
        Department department = mock(Department.class);
        doNothing().when(department).setEmployees(Mockito.<Set<Employee>>any());
        doNothing().when(department).setId(anyLong());
        doNothing().when(department).setName(Mockito.<String>any());
        department.setEmployees(new HashSet<>());
        department.setId(1L);
        department.setName("Name");

        Employee employee = new Employee();
        employee.setDepartment(department);
        employee.setId(1L);
        employee.setName(null);

        Department department2 = new Department();
        department2.setEmployees(new HashSet<>());
        department2.setId(1L);
        department2.setName("Name");

        Employee employee2 = new Employee();
        employee2.setDepartment(department2);
        employee2.setId(1L);
        employee2.setName("Name");

        // Act and Assert
        assertNotEquals(employee, employee2);
    }

    /**
     * Method under test: {@link Employee#equals(Object)}
     */
    @Test
    void testEquals_whenOtherIsDifferent_thenReturnNotEqual4() {
        // Arrange
        Department department = mock(Department.class);
        doNothing().when(department).setEmployees(Mockito.<Set<Employee>>any());
        doNothing().when(department).setId(anyLong());
        doNothing().when(department).setName(Mockito.<String>any());
        department.setEmployees(new HashSet<>());
        department.setId(1L);
        department.setName("Name");

        Employee employee = new Employee();
        employee.setDepartment(department);
        employee.setId(1L);
        employee.setName("com.example.CRUDPostgres.Employee.entity.Employee");

        Department department2 = new Department();
        department2.setEmployees(new HashSet<>());
        department2.setId(1L);
        department2.setName("Name");

        Employee employee2 = new Employee();
        employee2.setDepartment(department2);
        employee2.setId(1L);
        employee2.setName("Name");

        // Act and Assert
        assertNotEquals(employee, employee2);
    }

    /**
     * Method under test: {@link Employee#equals(Object)}
     */
    @Test
    void testEquals_whenOtherIsDifferent_thenReturnNotEqual5() {
        // Arrange
        Department department = mock(Department.class);
        doNothing().when(department).setEmployees(Mockito.<Set<Employee>>any());
        doNothing().when(department).setId(anyLong());
        doNothing().when(department).setName(Mockito.<String>any());
        department.setEmployees(new HashSet<>());
        department.setId(1L);
        department.setName("Name");

        Employee employee = new Employee();
        employee.setDepartment(department);
        employee.setId(1L);
        employee.setName(null);

        Department department2 = new Department();
        department2.setEmployees(new HashSet<>());
        department2.setId(1L);
        department2.setName("Name");

        Employee employee2 = new Employee();
        employee2.setDepartment(department2);
        employee2.setId(1L);
        employee2.setName(null);

        // Act and Assert
        assertNotEquals(employee, employee2);
    }

    /**
     * Method under test: {@link Employee#equals(Object)}
     */
    @Test
    void testEquals_whenOtherIsNull_thenReturnNotEqual() {
        // Arrange
        Department department = new Department();
        department.setEmployees(new HashSet<>());
        department.setId(1L);
        department.setName("Name");

        Employee employee = new Employee();
        employee.setDepartment(department);
        employee.setId(1L);
        employee.setName("Name");

        // Act and Assert
        assertNotEquals(employee, null);
    }

    /**
     * Method under test: {@link Employee#equals(Object)}
     */
    @Test
    void testEquals_whenOtherIsWrongType_thenReturnNotEqual() {
        // Arrange
        Department department = new Department();
        department.setEmployees(new HashSet<>());
        department.setId(1L);
        department.setName("Name");

        Employee employee = new Employee();
        employee.setDepartment(department);
        employee.setId(1L);
        employee.setName("Name");

        // Act and Assert
        assertNotEquals(employee, "Different type to Employee");
    }

    /**
     * Methods under test:
     * <ul>
     *   <li>{@link Employee#Employee()}
     *   <li>{@link Employee#setDepartment(Department)}
     *   <li>{@link Employee#setId(long)}
     *   <li>{@link Employee#setName(String)}
     *   <li>{@link Employee#toString()}
     *   <li>{@link Employee#getDepartment()}
     *   <li>{@link Employee#getId()}
     *   <li>{@link Employee#getName()}
     * </ul>
     */
    @Test
    void testGettersAndSetters() {
        // Arrange and Act
        Employee actualEmployee = new Employee();
        Department department = new Department();
        department.setEmployees(new HashSet<>());
        department.setId(1L);
        department.setName("Name");
        actualEmployee.setDepartment(department);
        actualEmployee.setId(1L);
        actualEmployee.setName("Name");
        String actualToStringResult = actualEmployee.toString();
        Department actualDepartment = actualEmployee.getDepartment();
        long actualId = actualEmployee.getId();

        // Assert that nothing has changed
        assertEquals("Employee(id=1, name=Name, department=Department(id=1, name=Name, employees=[]))",
                actualToStringResult);
        assertEquals("Name", actualEmployee.getName());
        assertEquals(1L, actualId);
        assertSame(department, actualDepartment);
    }

    /**
     * Method under test: {@link Employee#Employee(long, String, Department)}
     */
    @Test
    void testNewEmployee() {
        // Arrange
        Department department = new Department();
        department.setEmployees(new HashSet<>());
        department.setId(1L);
        department.setName("Name");

        // Act
        Employee actualEmployee = new Employee(1L, "Name", department);

        // Assert
        assertEquals("Name", actualEmployee.getName());
        assertEquals(1L, actualEmployee.getId());
        assertSame(department, actualEmployee.getDepartment());
    }

    /**
     * Method under test: {@link Employee#Employee(long, String, Department)}
     */
    @Test
    void testNewEmployee2() {
        // Arrange
        Department department = mock(Department.class);
        doNothing().when(department).setEmployees(Mockito.<Set<Employee>>any());
        doNothing().when(department).setId(anyLong());
        doNothing().when(department).setName(Mockito.<String>any());
        department.setEmployees(new HashSet<>());
        department.setId(1L);
        department.setName("Name");

        // Act
        Employee actualEmployee = new Employee(1L, "Name", department);

        // Assert
        verify(department).setEmployees(isA(Set.class));
        verify(department).setId(eq(1L));
        verify(department).setName(eq("Name"));
        assertEquals("Name", actualEmployee.getName());
        assertEquals(1L, actualEmployee.getId());
        assertSame(department, actualEmployee.getDepartment());
    }
}
