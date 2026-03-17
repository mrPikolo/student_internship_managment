package faculty_app.dto;

import java.time.LocalDate;

public record UpdateStudentRequest(String firstName, String lastName, String email, 
									LocalDate birthDate, String university,String faculty,
										String yearOfStudy, String gpa, String username, String password) {

}
