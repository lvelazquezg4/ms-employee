package mx.com.ms.utils;

/**
 * @project ms-employees
 * @created 25/02/2026
 * @author lvg
 */

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import mx.com.ms.dto.EmployeeFilterDTO;
import mx.com.ms.entity.EmployeeEntity;

@Component
public class SpecificationFilter {

    public static Specification<EmployeeEntity> fromFilter(EmployeeFilterDTO filter) {
    	
    	return ((root, query, cb) -> {

    	    List<Predicate> predicates = new ArrayList<>();
    	    
    	    if (filter.getFirstName() != null && !filter.getFirstName().isEmpty()) {
    	        predicates.add(cb.like(cb.lower(root.get("firstName")),
    	                               "%" + filter.getFirstName().toLowerCase() + "%"));
    	    }

            if (filter.getFirstName() != null && !filter.getFirstName().isEmpty()) {
            	predicates.add(cb.like(cb.lower(root.get("firstName")), 
            			"%" + filter.getFirstName().toLowerCase() + "%"));
            }
            
            if (filter.getSecondName() != null && !filter.getSecondName().isEmpty()) {
            	predicates.add(cb.like(cb.lower(root.get("secondName")), 
            			"%" + filter.getSecondName().toLowerCase() + "%"));
            }
            if (filter.getParentName() != null && !filter.getParentName().isEmpty()) {
            	predicates.add(cb.like(cb.lower(root.get("parentNane")), 
            			"%" + filter.getParentName().toLowerCase() + "%"));
            }
            if (filter.getMotherName() != null && !filter.getMotherName().isEmpty()) {
            	predicates.add(cb.like(cb.lower(root.get("motherName")), 
            			"%" + filter.getMotherName().toLowerCase() + "%"));
            }
            if (filter.getAge() != null) {
            	predicates.add(cb.equal(root.get("age"), filter.getAge()));
            }
            if (filter.getStatus() != null) {
            	predicates.add(cb.equal(root.get("status"), filter.getStatus()));
            }

    	    if (predicates.isEmpty()) {
    	    	return cb.conjunction();
    	    }

    	    query.orderBy(cb.desc(root.get("startDate")));

    	    return cb.and(predicates.toArray(new Predicate[0]));
    	});
    }
}
