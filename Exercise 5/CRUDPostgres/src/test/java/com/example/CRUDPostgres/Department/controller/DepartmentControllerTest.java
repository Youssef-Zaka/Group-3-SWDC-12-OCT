package com.example.CRUDPostgres.Department.controller;


import com.example.CRUDPostgres.Department.dto.DepartmentDTO;
import com.example.CRUDPostgres.Department.exceptions.DepartmentNotFoundException;
import com.example.CRUDPostgres.Department.exceptions.DuplicateDepartmentException;
import com.example.CRUDPostgres.Department.services.DepartmentService;
import jakarta.validation.ConstraintViolationException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;
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
    void testCreateDepartment() throws Exception {
        when(departmentService.createDepartment(any(DepartmentDTO.class))).thenReturn(departmentDTO1);

        mockMvc.perform(post("/departments")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\":\"HR\"}"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.name").value("HR"))
                .andDo(print());
    }

    @Test
    void testGetAllDepartments() throws Exception {
        List<DepartmentDTO> departmentList = Arrays.asList(departmentDTO1, departmentDTO2);
        when(departmentService.getAllDepartments()).thenReturn(departmentList);

        mockMvc.perform(get("/departments"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1L))
                .andExpect(jsonPath("$[0].name").value("HR"))
                .andExpect(jsonPath("$[1].id").value(2L))
                .andExpect(jsonPath("$[1].name").value("IT"))
                .andDo(print());
    }

    @Test
    void testGetDepartmentById() throws Exception {
        when(departmentService.getDepartmentById(1L)).thenReturn(departmentDTO1);

        mockMvc.perform(get("/departments/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.name").value("HR"))
                .andDo(print());
    }

    @Test
    void testGetDepartmentByIdNotFound() throws Exception {
        when(departmentService.getDepartmentById(1L)).thenReturn(null);

        mockMvc.perform(get("/departments/1"))
                .andExpect(status().isNotFound())
                .andDo(print());
    }

    @Test
    void testDeleteDepartment() throws Exception {
        Mockito.doNothing().when(departmentService).deleteDepartment(1L);

        mockMvc.perform(delete("/departments/1"))
                .andExpect(status().isNoContent())
                .andDo(print());
    }

}