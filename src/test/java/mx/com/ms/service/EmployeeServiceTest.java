package mx.com.ms.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import mx.com.ms.dto.EmployeeRequestDTO;
import mx.com.ms.dto.EmployeeResponseDTO;
import mx.com.ms.entity.EmployeeEntity;
import mx.com.ms.mapper.EmployeeMapper;
import mx.com.ms.repository.EmployeeRepository;
import mx.com.ms.service.impl.EmployeeServiceImpl;

@ExtendWith(MockitoExtension.class)
class EmployeeServiceImplTest {

    @Mock
    private EmployeeRepository repository;

    @Mock
    private EmployeeMapper mapper;

    @InjectMocks
    private EmployeeServiceImpl service;

    private EmployeeEntity entity;
    private EmployeeRequestDTO requestDTO;
    private EmployeeResponseDTO responseDTO;

    @BeforeEach
    void setUp() {
        entity = new EmployeeEntity();
        entity.setId(1L);
        entity.setFirstName("Pedro");
        entity.setGender("Hombre");
        entity.setBirthDate(new Date());

        requestDTO = new EmployeeRequestDTO();
        requestDTO.setFirstName("Pedro");
        requestDTO.setGender("Hombre");
        requestDTO.setBirthDate("2000-01-01");

        responseDTO = new EmployeeResponseDTO();
        responseDTO.setFirstName("Pedro");
        responseDTO.setGender("Hombre");
        responseDTO.setBirthDate("2000-01-01");
    }
    
    @Test
    void findAll_shouldReturnListOfEmployees() {
        when(repository.findAll()).thenReturn(List.of(entity));
        when(mapper.toResponseDTO(entity)).thenReturn(responseDTO);

        List<EmployeeResponseDTO> result = service.findAll();

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("Pedro", result.get(0).getFirstName());
    }
    
    @Test
    void updateExistingEmployeeShouldReturnUpdated() {
        when(repository.findById(1L)).thenReturn(Optional.of(entity));
        when(repository.save(entity)).thenReturn(entity);
        when(mapper.toResponseDTO(entity)).thenReturn(responseDTO);

        EmployeeResponseDTO result = service.update(1L, requestDTO);

        assertEquals("Pedro", result.getFirstName());
    }

    @Test
    void updateNonExistingEmployeeShouldThrowException() {
        when(repository.findById(2L)).thenReturn(Optional.empty());

        RuntimeException ex = assertThrows(RuntimeException.class, () -> service.update(2L, requestDTO));
        assertEquals("Employee not found", ex.getMessage());
    }

    @Test
    void findAllShouldReturnListOfEmployees() {
        when(repository.findAll()).thenReturn(List.of(entity));
        when(mapper.toResponseDTO(entity)).thenReturn(responseDTO);

        List<EmployeeResponseDTO> result = service.findAll();

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("Pedro", result.get(0).getFirstName());
    }
    
    @Test
    void findByIdExistingIdShouldReturnEmployee() {
        when(repository.findById(1L)).thenReturn(Optional.of(entity));
        when(mapper.toResponseDTO(entity)).thenReturn(responseDTO);

        EmployeeResponseDTO result = service.findById(1L);

        assertEquals("Pedro", result.getFirstName());
    }

    @Test
    void findByIdNonExistingIdShouldThrowException() {
        when(repository.findById(2L)).thenReturn(Optional.empty());

        RuntimeException ex = assertThrows(RuntimeException.class, () -> service.findById(2L));
        assertEquals("Employee not exist.", ex.getMessage());
    }

    @Test
    void deleteExistingNonEmployeeShouldCallRepositoryDelete() {
        when(repository.existsById(1L)).thenReturn(false);

        RuntimeException ex = assertThrows(RuntimeException.class, () -> service.delete(1L));
        assertEquals("Employee not found", ex.getMessage());
    }
    
    @Test
    void deleteExistingEmployeeShouldCallRepositoryDelete() {
        Long id = 1L;
        when(repository.existsById(id)).thenReturn(true);
        service.delete(id);
        verify(repository, times(1)).deleteById(id);
    }
}