package mx.com.ms.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

import mx.com.ms.dto.EmployeeRequestDTO;
import mx.com.ms.dto.EmployeeResponseDTO;
import mx.com.ms.services.EmployeeService;

@SpringBootTest
@AutoConfigureMockMvc
class EmployeeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private EmployeeService service;

    @Autowired
    private ObjectMapper objectMapper;
    
    @Test
    void testGetAllEmployees() throws Exception {
        EmployeeResponseDTO dto = new EmployeeResponseDTO();
        dto.setFirstName("Luis");
        when(service.findAll()).thenReturn(List.of(dto));

        mockMvc.perform(get("/api/employees"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].firstName").value("Luis"));
    }

    @Test
    void testCreateEmployeeValidationError() throws Exception {
        String json = """
            [
                {
                    "firstName": "",
                    "gender": "INVALID",
                    "birthDate": "1990-01-01"
                }
            ]
            """;

        mockMvc.perform(post("/api/employees")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isBadRequest());
    }
    
    @Test
    void testPutEmployee() throws Exception {
        Long id = 1L;
        EmployeeRequestDTO inputEmployee = new EmployeeRequestDTO();
        inputEmployee.setFirstName("Luis");

        EmployeeResponseDTO returnedEmployee = new EmployeeResponseDTO();
        returnedEmployee.setFirstName("Pedro");

        when(service.update(eq(id), any(EmployeeRequestDTO.class))).thenReturn(returnedEmployee);

        mockMvc.perform(put("/api/employees/{id}", id)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(inputEmployee)))
               .andExpect(status().isOk())
               .andExpect(jsonPath("$.firstName").value("Pedro"));

        verify(service, times(1)).update(eq(id), any(EmployeeRequestDTO.class));
    }
    
    @Test
    void testDeleteEmployee() throws Exception {
        Long id = 1L;
        doNothing().when(service).delete(id);

        mockMvc.perform(delete("/api/employees/{id}", id))
               .andExpect(status().isNoContent());
        verify(service, times(1)).delete(id);
    }
}