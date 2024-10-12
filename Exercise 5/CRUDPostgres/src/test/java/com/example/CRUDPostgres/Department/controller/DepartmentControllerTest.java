package com.example.CRUDPostgres.Department.controller;

import com.example.CRUDPostgres.Department.dto.DepartmentDTO;
import com.example.CRUDPostgres.Department.services.DepartmentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@WebMvcTest(DepartmentController.class)
class DepartmentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private DepartmentService departmentService;

    private DepartmentDTO departmentDTO1;
    private DepartmentDTO departmentDTO2;

    @BeforeEach
    void setUp() {
        departmentDTO1 = new DepartmentDTO();
        departmentDTO1.setId(1L);
        departmentDTO1.setName("HR");

        departmentDTO2 = new DepartmentDTO();
        departmentDTO2.setId(2L);
        departmentDTO2.setName("IT");
    }

    @Test
    @WithMockUser(roles = "USER")  // Mock an authenticated user with USER role
    void testCreateDepartment() throws Exception {
        when(departmentService.createDepartment(any(DepartmentDTO.class))).thenReturn(departmentDTO1);

        mockMvc.perform(post("/api/departments")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\":\"HR\"}"))
                .andExpect(status().isCreated())  // Expecting 201 Created
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.name").value("HR"))
                .andDo(print());
    }

    @Test
    @WithMockUser(roles = "USER")  // Mock an authenticated user with USER role
    void testGetAllDepartments() throws Exception {
        List<DepartmentDTO> departmentList = Arrays.asList(departmentDTO1, departmentDTO2);
        when(departmentService.getAllDepartments()).thenReturn(departmentList);

        mockMvc.perform(get("/api/departments"))
                .andExpect(status().isOk())  // Expecting 200 OK
                .andExpect(jsonPath("$[0].id").value(1L))
                .andExpect(jsonPath("$[0].name").value("HR"))
                .andExpect(jsonPath("$[1].id").value(2L))
                .andExpect(jsonPath("$[1].name").value("IT"))
                .andDo(print());
    }

    @Test
    @WithMockUser(roles = "USER")  // Mock an authenticated user with USER role
    void testGetDepartmentById() throws Exception {
        when(departmentService.getDepartmentById(1L)).thenReturn(departmentDTO1);

        mockMvc.perform(get("/api/departments/1"))
                .andExpect(status().isOk())  // Expecting 200 OK
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.name").value("HR"))
                .andDo(print());
    }

    @Test
    @WithMockUser(roles = "USER")  // Mock an authenticated user with USER role
    void testGetDepartmentByIdNotFound() throws Exception {
        when(departmentService.getDepartmentById(1L)).thenReturn(null);

        mockMvc.perform(get("/api/departments/1"))
                .andExpect(status().isNotFound())  // Expecting 404 Not Found
                .andDo(print());
    }

    @Test
    @WithMockUser(roles = "ADMIN")  // Mock an authenticated user with ADMIN role
    void testDeleteDepartment() throws Exception {
        Mockito.doNothing().when(departmentService).deleteDepartment(1L);

        mockMvc.perform(delete("/api/departments/1"))
                .andExpect(status().isNoContent())  // Expecting 204 No Content
                .andDo(print());
    }
}
