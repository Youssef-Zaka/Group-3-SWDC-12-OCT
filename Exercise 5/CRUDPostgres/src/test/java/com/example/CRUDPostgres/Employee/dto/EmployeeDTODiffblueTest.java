package com.example.CRUDPostgres.Employee.dto;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class EmployeeDTODiffblueTest {
    /**
     * Methods under test:
     * <ul>
     *   <li>default or parameterless constructor of {@link EmployeeDTO}
     *   <li>{@link EmployeeDTO#setDepartmentId(Long)}
     *   <li>{@link EmployeeDTO#setId(Long)}
     *   <li>{@link EmployeeDTO#setName(String)}
     *   <li>{@link EmployeeDTO#toString()}
     *   <li>{@link EmployeeDTO#getDepartmentId()}
     *   <li>{@link EmployeeDTO#getId()}
     *   <li>{@link EmployeeDTO#getName()}
     * </ul>
     */
    @Test
    void testGettersAndSetters() {
        // Arrange and Act
        EmployeeDTO actualEmployeeDTO = new EmployeeDTO();
        actualEmployeeDTO.setDepartmentId(1L);
        actualEmployeeDTO.setId(1L);
        actualEmployeeDTO.setName("Name");
        String actualToStringResult = actualEmployeeDTO.toString();
        Long actualDepartmentId = actualEmployeeDTO.getDepartmentId();
        Long actualId = actualEmployeeDTO.getId();

        // Assert that nothing has changed
        assertEquals("EmployeeDTO(id=1, name=Name, departmentId=1)", actualToStringResult);
        assertEquals("Name", actualEmployeeDTO.getName());
        assertEquals(1L, actualDepartmentId.longValue());
        assertEquals(1L, actualId.longValue());
    }
}
