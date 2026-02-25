package mx.com.ms.services;

/**
 * @project ms-employees
 * @created 25/02/2026
 * @author lvg
 */
import java.util.List;

import mx.com.ms.dto.EmployeeFilterDTO;
import mx.com.ms.dto.EmployeeRequestDTO;
import mx.com.ms.dto.EmployeeResponseDTO;

public interface EmployeeService {
	
	/**
	 * Method for find all employees
	 * @return
	 */
	List<EmployeeResponseDTO> findAll();

	/**
	 * Method for find a employee by id
	 * @param id
	 * @return
	 */
	EmployeeResponseDTO findById(Long id);

	/**
	 * Method for save employees
	 * @param List<employees>
	 * @return
	 */
	List<EmployeeResponseDTO> saveAll(List<EmployeeRequestDTO> employees);

	/**
	 * Method for update employee by id 
	 * @param id
	 * @param employees
	 * @return
	 */
	EmployeeResponseDTO update(Long id, EmployeeRequestDTO employees);

	/**
	 * Method for delete employee by id
	 * @param id
	 */
	void delete(Long id);

	/**
	 * Method for search employee by filters
	 * @param employeeFilterDTO
	 * @return
	 */
	List<EmployeeResponseDTO> searchByName(EmployeeFilterDTO employeeFilterDTO);
}
