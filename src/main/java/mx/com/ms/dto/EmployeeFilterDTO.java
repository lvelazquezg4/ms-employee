package mx.com.ms.dto;

/**
 * @project ms-employees
 * @created 25/02/2026
 * @author lvg
 */

public class EmployeeFilterDTO extends EmployeeRequestDTO {

	private static final long serialVersionUID = -2916386349053968201L;
	
	public String id;
	public String startDate;
	
	public EmployeeFilterDTO(String id, String startDate) {
		super();
		this.id = id;
		this.startDate = startDate;
	}

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	
	
}
