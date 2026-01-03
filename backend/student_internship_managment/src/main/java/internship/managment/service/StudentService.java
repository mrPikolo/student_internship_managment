package internship.managment.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import internship.managment.component.StudentMapper;
import internship.managment.dto.CreateStudentDTO;
import internship.managment.dto.StudentDTO;
import internship.managment.model.Role;
import internship.managment.model.Student;
import internship.managment.model.User;
import internship.managment.repository.StudentRepository;
import internship.managment.repository.UserRepository;

@Service
public class StudentService {
	
	@Autowired
	private StudentRepository studentRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
    private StudentMapper mapper;

	public StudentDTO create(CreateStudentDTO dto) {
		
		User user = new User();
		user.setUsername(dto.getUsername());
		user.setPassword("student");
		user.setRole(Role.STUDENT);
		user.setActive(true);
		
		userRepository.save(user);
		
		Student student = new Student();
		student.setFirstName(dto.getFirstName());
		student.setLastName(dto.getLastName());
		student.setIndexNumber(dto.getIndexNumber());
		student.setEmail(dto.getEmail());
		student.setBirthDate(dto.getBirthDate());
		student.setUser(user);
		
		Student saved = studentRepository.save(student);
		
		return mapper.toDTO(saved);
	}

	public List<StudentDTO> getAll() {
		
		List<Student> all = studentRepository.findAll();
		
		List<StudentDTO> allDTO = new ArrayList<>();
		for(Student s: all) {
			StudentDTO sDTO = mapper.toDTO(s);
			allDTO.add(sDTO);
		}
		
		return allDTO;
	}

}
