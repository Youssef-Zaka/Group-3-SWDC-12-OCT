package com.example.CRUDPostgres.Department.dto;


import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for DepartmentDTO class.
 */
public class DepartmentDTOTest {

    private Validator validator;

    @BeforeEach
    public void setUp() {
        // Setting up the validator for bean validation tests
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    public void testValidDepartmentDTO() {
        // Create a valid DepartmentDTO
        DepartmentDTO departmentDTO = new DepartmentDTO();
        departmentDTO.setId(1L);
        departmentDTO.setName("HR");

        // Validate the object (should pass with no violations)
        Set<ConstraintViolation<DepartmentDTO>> violations = validator.validate(departmentDTO);

        // Asserting that no validation violations occurred
        assertTrue(violations.isEmpty(), "Expected no validation violations for valid DepartmentDTO");
    }

    @Test
    public void testDepartmentDTOWithBlankName() {
        // Create a DepartmentDTO with a blank name (violating @NotBlank)
        DepartmentDTO departmentDTO = new DepartmentDTO();
        departmentDTO.setId(1L);
        departmentDTO.setName(""); // Invalid name (blank)

        // Validate the object (should fail due to blank name)
        Set<ConstraintViolation<DepartmentDTO>> violations = validator.validate(departmentDTO);

        // Asserting that the violation occurred
        assertFalse(violations.isEmpty(), "Expected validation violation for blank department name");
        assertEquals(1, violations.size(), "Expected one violation for blank name");
        assertEquals("Department name cannot be blank", violations.iterator().next().getMessage(), "Expected 'blank name' violation message");
    }

    @Test
    public void testDepartmentDTOWithNullName() {
        // Create a DepartmentDTO with a null name (violating @NotBlank)
        DepartmentDTO departmentDTO = new DepartmentDTO();
        departmentDTO.setId(1L);
        departmentDTO.setName(null); // Invalid name (null)

        // Validate the object (should fail due to null name)
        Set<ConstraintViolation<DepartmentDTO>> violations = validator.validate(departmentDTO);

        // Asserting that the violation occurred
        assertFalse(violations.isEmpty(), "Expected validation violation for null department name");
        assertEquals(1, violations.size(), "Expected one violation for null name");
        assertEquals("Department name cannot be blank", violations.iterator().next().getMessage(), "Expected 'null name' violation message");
    }

    @Test
    public void testDepartmentDTOIdGetterAndSetter() {
        // Create a DepartmentDTO and set the ID
        DepartmentDTO departmentDTO = new DepartmentDTO();
        departmentDTO.setId(100L);

        // Verify the ID getter and setter
        assertEquals(100L, departmentDTO.getId(), "Expected the ID to be 100");
    }

    @Test
    public void testDepartmentDTONameGetterAndSetter() {
        // Create a DepartmentDTO and set the name
        DepartmentDTO departmentDTO = new DepartmentDTO();
        departmentDTO.setName("Finance");

        // Verify the name getter and setter
        assertEquals("Finance", departmentDTO.getName(), "Expected the name to be 'Finance'");
    }

    @Test
    public void testDepartmentDTOToString() {
        // Create a DepartmentDTO to test the Lombok-generated toString method
        DepartmentDTO departmentDTO = new DepartmentDTO();
        departmentDTO.setId(1L);
        departmentDTO.setName("IT");

        // Asserting the toString method
        String expectedToString = "DepartmentDTO(id=1, name=IT)";
        assertEquals(expectedToString, departmentDTO.toString(), "Expected toString representation doesn't match");
    }

    @Test
    public void testDepartmentDTOEqualsAndHashCode() {
        // Create two DepartmentDTOs with the same data
        DepartmentDTO departmentDTO1 = new DepartmentDTO();
        departmentDTO1.setId(1L);
        departmentDTO1.setName("HR");

        DepartmentDTO departmentDTO2 = new DepartmentDTO();
        departmentDTO2.setId(1L);
        departmentDTO2.setName("HR");

        // Test equals and hashCode methods
        assertEquals(departmentDTO1, departmentDTO2, "Expected DepartmentDTOs to be equal");
        assertEquals(departmentDTO1.hashCode(), departmentDTO2.hashCode(), "Expected hash codes to be equal");
    }

    @Test
    public void testDepartmentDTONoArgsConstructor() {
        // Test the no-args constructor
        DepartmentDTO departmentDTO = new DepartmentDTO();

        // Asserting that the default constructor initializes fields to null
        assertNull(departmentDTO.getId(), "Expected id to be null");
        assertNull(departmentDTO.getName(), "Expected name to be null");
    }
}