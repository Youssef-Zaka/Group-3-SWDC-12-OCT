package com.example.CRUDPostgres.Department.repository;

import com.example.CRUDPostgres.Department.entity.Department;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class DepartmentRepositoryTest {

    @Autowired
    private DepartmentRepository departmentRepository;

    private Department savedDepartment;

    @BeforeEach
    void setUp() {
        // Clear the repository to avoid conflicts
        departmentRepository.deleteAll();

        // Save a few departments before each test
        Department department1 = new Department();
        department1.setName("Finance");
        savedDepartment = departmentRepository.save(department1);

        Department department2 = new Department();
        department2.setName("Engineering");
        departmentRepository.save(department2);
    }

    @Test
    void testFindById_ExistingDepartment() {
        // Act: Find department by the saved department's ID
        Optional<Department> foundDepartment = departmentRepository.findById(savedDepartment.getId());

        // Assert: Verify that the department was found
        assertThat(foundDepartment).isPresent();
        assertThat(foundDepartment.get().getName()).isEqualTo("Finance");
    }

    @Test
    void testFindById_NonExistingDepartment() {
        // Act: Try to find a department by an ID that does not exist
        Optional<Department> foundDepartment = departmentRepository.findById(100L);

        // Assert: Verify that no department was found
        assertThat(foundDepartment).isNotPresent();
    }

    @Test
    void testSaveDepartment() {
        // Arrange: Create a new department
        Department newDepartment = new Department();
        newDepartment.setName("HR");

        // Act: Save the department
        Department savedDepartment = departmentRepository.save(newDepartment);

        // Assert: Verify the department was saved correctly
        assertThat(savedDepartment.getId()).isNotNull();
        assertThat(savedDepartment.getName()).isEqualTo("HR");
    }

    @Test
    void testUpdateDepartment() {
        // Act: Find an existing department and update its name
        Optional<Department> foundDepartment = departmentRepository.findById(savedDepartment.getId());
        assertThat(foundDepartment).isPresent();

        Department departmentToUpdate = foundDepartment.get();
        departmentToUpdate.setName("Updated Finance");

        // Act: Save the updated department
        Department updatedDepartment = departmentRepository.save(departmentToUpdate);

        // Assert: Verify the department was updated correctly
        assertThat(updatedDepartment.getName()).isEqualTo("Updated Finance");

        // Verify it's updated in the database
        Optional<Department> verifyUpdatedDepartment = departmentRepository.findById(savedDepartment.getId());
        assertThat(verifyUpdatedDepartment).isPresent();
        assertThat(verifyUpdatedDepartment.get().getName()).isEqualTo("Updated Finance");
    }

    @Test
    void testFindByName_ExistingDepartment() {
        // Act: Find department by name
        Department foundDepartment = departmentRepository.findByName("Finance");

        // Assert: Verify that the department was found
        assertThat(foundDepartment).isNotNull();
        assertThat(foundDepartment.getName()).isEqualTo("Finance");
    }

    @Test
    void testFindByName_NonExistingDepartment() {
        // Act: Try to find a department by a name that does not exist
        Department foundDepartment = departmentRepository.findByName("HR");

        // Assert: Verify that no department was found
        assertThat(foundDepartment).isNull();
    }

    @Test
    void testDeleteDepartment() {
        // Act: Delete a department by ID
        departmentRepository.deleteById(savedDepartment.getId());

        // Assert: Verify that the department is deleted
        Optional<Department> deletedDepartment = departmentRepository.findById(savedDepartment.getId());
        assertThat(deletedDepartment).isNotPresent();
    }
}