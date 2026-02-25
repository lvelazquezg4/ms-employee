package mx.com.ms.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import mx.com.ms.dto.EmployeeRequestDTO;
import mx.com.ms.dto.EmployeeResponseDTO;
import mx.com.ms.entity.EmployeeEntity;
import mx.com.ms.repository.EmployeeRepository;
import mx.com.ms.services.EmployeeService;

@SpringBootTest
public class EmployeeServiceTestH2 {

    @Autowired
    private EmployeeRepository repository;


    @Autowired
    private EmployeeService service;

    private EmployeeRequestDTO requestDTO;

    @BeforeEach
    void setUp() {
        repository.deleteAll();

        requestDTO = new EmployeeRequestDTO();
        requestDTO.setFirstName("Ana");
        requestDTO.setGender("FEMALE");
        requestDTO.setBirthDate("1995-05-15");
        requestDTO.setParentName("Perez");
        requestDTO.setMotherName("Lopez");
        requestDTO.setAge(28);
    }

    @Test
    void createEmployee_shouldPersistToH2() {
        List<EmployeeResponseDTO> result = service.saveAll(List.of(requestDTO));

        assertEquals(1, result.size());
        assertEquals("Ana", result.get(0).getFirstName());

        List<EmployeeEntity> entities = repository.findAll();
        assertEquals(1, entities.size());
        assertEquals("Ana", entities.get(0).getFirstName());
    }

    @Test
    void findAll_shouldReturnPersistedEmployees() {
        service.saveAll(List.of(requestDTO));

        List<EmployeeResponseDTO> result = service.findAll();

        assertEquals(1, result.size());
        assertEquals("Ana", result.get(0).getFirstName());
    }

    @Test
    void findById_nonExisting_shouldThrowException() {
        RuntimeException ex = assertThrows(RuntimeException.class, () -> service.findById(999L));
        assertEquals("Employee not exist.", ex.getMessage());
    }
}