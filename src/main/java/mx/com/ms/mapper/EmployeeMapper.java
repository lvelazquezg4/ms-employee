package mx.com.ms.mapper;

/**
 * @project ms-employees
 * @created 25/02/2026
 * @author lvg
 */

import java.text.ParseException;

import org.springframework.stereotype.Component;
import mx.com.ms.dto.EmployeeRequestDTO;
import mx.com.ms.dto.EmployeeResponseDTO;
import mx.com.ms.entity.EmployeeEntity;
import mx.com.ms.exception.GenericException;
import mx.com.ms.utils.ConstantsUtils;

@Component
public class EmployeeMapper {

    public EmployeeResponseDTO toResponseDTO(EmployeeEntity entity) {
    	EmployeeResponseDTO employeeResponseDTO = new EmployeeResponseDTO();
    	employeeResponseDTO.setFirstName(entity.getFirstName());
    	employeeResponseDTO.setSecondName(entity.getSecondName());
    	employeeResponseDTO.setParentName(entity.getParentName());
    	employeeResponseDTO.setMotherName(entity.getMotherName());
    	employeeResponseDTO.setAge(entity.getAge());
    	employeeResponseDTO.setGender(entity.getGender());
    	employeeResponseDTO.setPosition(entity.getPosition());
    	employeeResponseDTO.setStatus(entity.getStatus());
        if(entity.getBirthDate() != null)
        	employeeResponseDTO.setBirthDate(ConstantsUtils.DATE_FORMATTER.format(entity.getBirthDate()).toString());
        else
        	employeeResponseDTO.setBirthDate("");
        if(entity.getStartDate() != null) 
        	employeeResponseDTO.setStartDate(ConstantsUtils.DATE_FORMATTER.format(entity.getStartDate()).toString());
        else
        	employeeResponseDTO.setStartDate("");
        return employeeResponseDTO;
    }


    public EmployeeEntity toEntity(EmployeeRequestDTO employeeRequestDTO) {
    	EmployeeEntity entity = new EmployeeEntity();
        entity.setFirstName(employeeRequestDTO.getFirstName());
        entity.setSecondName(employeeRequestDTO.getSecondName());
        entity.setParentName(employeeRequestDTO.getParentName());
        entity.setMotherName(employeeRequestDTO.getMotherName());
        entity.setAge(employeeRequestDTO.getAge());
        entity.setGender(employeeRequestDTO.getGender());
        entity.setPosition(employeeRequestDTO.getPosition());
    	entity.setStatus(employeeRequestDTO.getStatus());
        try {
        	entity.setBirthDate(ConstantsUtils.DATE_FORMATTER.parse(employeeRequestDTO.getBirthDate()));
        } catch (ParseException e) {
            throw new GenericException(ConstantsUtils.BR003, ConstantsUtils.BR003_TXT);
        }
        return entity;
    }
}