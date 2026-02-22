package faculty_app.dto;

import java.time.LocalDate;

public record UpdateStudentRequest(String firstName, String lastName, String email, 
									LocalDate birthDate, String indexNumber,String username) {

}
