package mx.com.ms.service.impl;

/**
 * @project ms-employees
 * @created 25/02/2026
 * @author lvg
 */

import java.text.ParseException;
import java.util.List;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import mx.com.ms.dto.EmployeeFilterDTO;
import mx.com.ms.dto.EmployeeRequestDTO;
import mx.com.ms.dto.EmployeeResponseDTO;
import mx.com.ms.entity.EmployeeEntity;
import mx.com.ms.exception.ResourceNotFoundException;
import mx.com.ms.mapper.EmployeeMapper;
import mx.com.ms.repository.EmployeeRepository;
import mx.com.ms.services.EmployeeService;
import mx.com.ms.utils.ConstantsUtils;
import mx.com.ms.utils.SpecificationFilter;

@Transactional
@Validated
@Service
public class EmployeeServiceImpl implements EmployeeService {
	
	private EmployeeRepository repository;
	private final EmployeeMapper mapper;
	
	public EmployeeServiceImpl(EmployeeRepository repository, EmployeeMapper mapper) {
		this.repository = repository;
		this.mapper = mapper;
	}

	@Override
	public List<EmployeeResponseDTO> findAll() {
		return repository.findAll()
				.stream()
				.map(mapper::toResponseDTO)
				.toList();
	}

	@Override
	public EmployeeResponseDTO findById(Long id) {
		EmployeeEntity employee = repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException(ConstantsUtils.BR001, ConstantsUtils.BR001_TXT));
		return mapper.toResponseDTO(employee);
	}

	@Override
	public List<EmployeeResponseDTO> saveAll(List<EmployeeRequestDTO> employees) {
		List<EmployeeEntity> entities = employees.stream()
				.map(mapper::toEntity)
				.toList();
		return repository.saveAll(entities)
				.stream()
				.map(mapper::toResponseDTO)
				.toList();
	}

	@Override
	public EmployeeResponseDTO update(Long id, EmployeeRequestDTO employees) {
		EmployeeEntity employeesSave = repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException(ConstantsUtils.BR000, ConstantsUtils.BR000_TXT));
        if (employees.getFirstName() != null) employeesSave.setFirstName(employees.getFirstName());
        if (employees.getSecondName() != null) employeesSave.setSecondName(employees.getSecondName());
        if (employees.getParentName() != null) employeesSave.setParentName(employees.getParentName());
        if (employees.getMotherName() != null) employeesSave.setMotherName(employees.getMotherName());
        if (employees.getAge() != null) employeesSave.setAge(employees.getAge());
        if (employees.getGender() != null) employeesSave.setGender(employees.getGender());
        if (employees.getPosition() != null) employeesSave.setPosition(employees.getPosition());
        if (employees.getStatus() != null) employeesSave.setStatus(employees.getStatus());
        
		try {
			if (employees.getBirthDate() != null) employeesSave.setBirthDate(ConstantsUtils.DATE_FORMATTER.parse(employees.getBirthDate()));
		} catch (ParseException e) {
			throw new IllegalArgumentException("birthDate must be a valid yyyy-MM-dd date");
		}
		return mapper.toResponseDTO(repository.save(employeesSave));
	}

	@Override
	public void delete(Long id) {
		if(!repository.existsById(id)) {
			throw new ResourceNotFoundException(ConstantsUtils.BR000,ConstantsUtils.BR000_TXT);
		}
		repository.deleteById(id);
	}

	@Override
	public List<EmployeeResponseDTO> searchByName(EmployeeFilterDTO employeeFilterDTO) {
		Specification<EmployeeEntity> specification = SpecificationFilter.fromFilter(employeeFilterDTO);
		List<EmployeeResponseDTO> result = repository.findAll(specification)
		        .stream()
		        .map(mapper::toResponseDTO)
		        .toList();
		if (result.isEmpty()) {
			throw new ResourceNotFoundException(ConstantsUtils.BR000,ConstantsUtils.BR000_TXT);
		}
		return result;
	}
}