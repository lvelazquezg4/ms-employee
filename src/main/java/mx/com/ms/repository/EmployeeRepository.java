package mx.com.ms.repository;

/**
 * @project ms-employees
 * @created 25/02/2026
 * @author lvg
 */

import java.util.List;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import mx.com.ms.entity.EmployeeEntity;

public interface EmployeeRepository extends JpaRepository<EmployeeEntity, Long>{
	
	/**
	 * method for find by name employee
	 * @param name
	 * @return
	 */
	List<EmployeeEntity> findByFirstNameContainingIgnoreCase(String name);

	/**
	 * method for find employee by filters
	 * @param specification
	 * @return
	 */
	List<EmployeeEntity> findAll(Specification<EmployeeEntity> specification);
	
}
