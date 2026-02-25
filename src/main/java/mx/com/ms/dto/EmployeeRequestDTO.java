package mx.com.ms.dto;

/**
 * @project ms-employees
 * @created 25/02/2026
 * @author lvg
 */

import java.io.Serializable;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@ToString
@Builder
@NoArgsConstructor
public class EmployeeRequestDTO implements Serializable {

	private static final long serialVersionUID = -7100070139584780964L;

	@NotBlank(message = "firstName is required")
	@Size(max = 50, message = "firstName must be not exceed to 50 characteres")
	private String firstName;
	
	public String secondName;
	
	@NotBlank(message = "parentName is requiered")
	@Size(max = 50, message = "parentName mus be not exceed to 50 characters")
	private String parentName;
	
	@NotBlank(message = "parentName is requiered")
	@Size(max = 50, message = "parentName mus be not exceed to 50 characters")
	private String motherName;
	
	@NotNull(message = "age is requiered")
	private Integer age;
	
	@NotBlank(message = "gender is requiered")
	@Pattern(regexp = "Hombre|Mujer", message = "gender must be Hombre or Mujer")
	private String gender;
	
	@NotNull(message = "birthDate is requiered")
	@Pattern(regexp = "^\\d{4}-(0[1-9]|1[0-2])-(0[1-9]|[12]\\d|3[01])$",
    message = "birthDate must be in yyyy-MM-dd format")
	private String birthDate;
	
	@NotBlank(message = "position is requiered")
	private String position;
	
	@NotNull(message = "status is requiered true=active, false=inactive")
	private Boolean status;

	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getSecondName() {
		return secondName;
	}
	public void setSecondName(String secondName) {
		this.secondName = secondName;
	}
	public String getParentName() {
		return parentName;
	}
	public void setParentName(String parentName) {
		this.parentName = parentName;
	}
	public String getMotherName() {
		return motherName;
	}
	public void setMotherName(String motherName) {
		this.motherName = motherName;
	}
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getBirthDate() {
		return birthDate;
	}
	public void setBirthDate(String birthDate) {
		this.birthDate = birthDate;
	}
	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
	}
	public Boolean getStatus() {
		return status;
	}
	public void setStatus(Boolean status) {
		this.status = status;
	}
}
