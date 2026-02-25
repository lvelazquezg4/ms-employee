package mx.com.ms.controller;

/**
 * @project ms-employees
 * @created 25/02/2026
 * @author lvg
 */

import java.util.List;

import javax.validation.Valid;

import org.springdoc.api.annotations.ParameterObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import mx.com.ms.dto.EmployeeFilterDTO;
import mx.com.ms.dto.EmployeeRequestDTO;
import mx.com.ms.dto.EmployeeResponseDTO;
import mx.com.ms.services.EmployeeService;

@RestController
@RequestMapping("/api/employees")
@Validated
public class EmployeeController {
	
	private EmployeeService service;
	
	public EmployeeController(EmployeeService service) {
		this.service = service;
	}
	
	@Operation(summary = "Get employees",
	           description = "Get all employees")
	@ApiResponses(value = {
	    @ApiResponse(responseCode = "200", description = "Query success"),
	    @ApiResponse(responseCode = "400", description = "Invalid fields"),
	})
	@GetMapping
	public ResponseEntity<List<EmployeeResponseDTO>> getAllEmployees() {
		return ResponseEntity.ok(service.findAll());
	}
	
	@Operation(summary = "Get employee",
	           description = "Get employee by id")
	@ApiResponses(value = {
	    @ApiResponse(responseCode = "200", description = "Query success"),
	    @ApiResponse(responseCode = "400", description = "Invalid fields"),
	})
	@GetMapping("/{id}")
	public ResponseEntity<EmployeeResponseDTO> getById(@PathVariable Long id) {
		return ResponseEntity.ok(service.findById(id));
	}
	
	@Operation(summary = "Crate employees",
	           description = "Creation employees")
	@ApiResponses(value = {
	    @ApiResponse(responseCode = "204", description = "No content and success delete"),
	    @ApiResponse(responseCode = "400", description = "Invalid fields"),
	    @ApiResponse(responseCode = "500", description = "Internal error")
	})
	@PostMapping
	public ResponseEntity<List<EmployeeResponseDTO>> create(@RequestBody List<@Valid EmployeeRequestDTO> employees) {
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(service.saveAll(employees));	
	}

	@Operation(summary = "Update employee",
	           description = "Update employee by id")
	@ApiResponses(value = {
	    @ApiResponse(responseCode = "200", description = "Update success"),
	    @ApiResponse(responseCode = "400", description = "Filtros inválidos"),
	    @ApiResponse(responseCode = "500", description = "Internal error")
	})
	@PutMapping("/{id}")
	public ResponseEntity<EmployeeResponseDTO> update(@PathVariable Long id,
													  @RequestBody EmployeeRequestDTO employee) {
		return ResponseEntity.ok(service.update(id, employee));
	}
	
	@Operation(summary = "Delete employee",
	           description = "Deelete employee by id")
	@ApiResponses(value = {
	    @ApiResponse(responseCode = "200", description = "Deleted success"),
	    @ApiResponse(responseCode = "401", description = "Not found"),
	    @ApiResponse(responseCode = "500", description = "Internal error")
	})
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}

	@Operation(summary = "Find employees",
	           description = "Find employees with filter as firsName or status")
	@ApiResponses(value = {
	    @ApiResponse(responseCode = "200", description = "Search success"),
	    @ApiResponse(responseCode = "400", description = "Invalid filters"),
	    @ApiResponse(responseCode = "500", description = "Internal error")
	})
	@GetMapping("/search")
	public ResponseEntity<List<EmployeeResponseDTO>> search(@ParameterObject EmployeeFilterDTO employeeFilterDTO) {
		return ResponseEntity.ok(service.searchByName(employeeFilterDTO));
	}

}