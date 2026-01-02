package internship.managment.dto;

import java.time.LocalDate;

public class CreateStudentDTO {
	
	private String firstName;
	private String lastName;
	private String email;
	private LocalDate birthDate;
	private String indexNumber;
	
	private String username;

	public CreateStudentDTO() {
		super();
	}

	public CreateStudentDTO(String firstName, String lastName, String email, LocalDate birthDate, String indexNumber,
			String username) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.birthDate = birthDate;
		this.indexNumber = indexNumber;
		this.username = username;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public LocalDate getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(LocalDate birthDate) {
		this.birthDate = birthDate;
	}

	public String getIndexNumber() {
		return indexNumber;
	}

	public void setIndexNumber(String indexNumber) {
		this.indexNumber = indexNumber;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	
	

}
