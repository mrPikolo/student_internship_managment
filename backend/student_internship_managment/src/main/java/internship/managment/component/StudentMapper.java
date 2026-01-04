package internship.managment.component;

import org.springframework.stereotype.Component;

import internship.managment.dto.StudentDTO;
import internship.managment.model.Student;

@Component
public class StudentMapper {

	public StudentDTO toDTO(Student student) {
        StudentDTO dto = new StudentDTO();
        dto.setId(student.getId());
        dto.setFirstName(student.getFirstName());
        dto.setLastName(student.getLastName());
        dto.setEmail(student.getEmail());
        dto.setIndexNumber(student.getIndexNumber());
        dto.setBirthDate(student.getBirthDate());
        dto.setUsername(student.getUser().getUsername());
        dto.setActive(student.getUser().isActive());
        return dto;
    }
}
