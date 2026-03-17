package internship.managment.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
		user.setPassword(dto.getPassword());
		user.setRole(Role.STUDENT);
		user.setActive(true);
		
		userRepository.save(user);
		
		Student student = new Student();
		student.setFirstName(dto.getFirstName());
		student.setLastName(dto.getLastName());
		student.setEmail(dto.getEmail());
		student.setBirthDate(dto.getBirthDate());
		student.setUniversity(dto.getUniversity());
		student.setFaculty(dto.getFaculty());
		student.setYearOfStudy(dto.getYearOfStudy());
		student.setGpa(dto.getGpa());
		student.setUser(user);
		
		Student saved = studentRepository.save(student);
		
		return mapper.toDTO(saved);
	}

	public List<StudentDTO> getAll() {
		
		List<Student> all = studentRepository.findAllActive();
		
		List<StudentDTO> allDTO = new ArrayList<>();
		for(Student s: all) {
			StudentDTO sDTO = mapper.toDTO(s);
			allDTO.add(sDTO);
		}
		
		return allDTO;
	}

	public StudentDTO updateStudent(Long id, CreateStudentDTO csDTO) {
		
		StudentDTO studentDTO= null;
		
		Optional<Student> studentOpt = studentRepository.findById(id);
		
		if(studentOpt.isPresent()) {
			Student s = studentOpt.get();
			s.setFirstName(csDTO.getFirstName());
			s.setLastName(csDTO.getLastName());
			s.setEmail(csDTO.getEmail());
			s.setBirthDate(csDTO.getBirthDate());
			s.setUniversity(csDTO.getUniversity());
			s.setFaculty(csDTO.getFaculty());
			s.setGpa(csDTO.getGpa());
			s.getUser().setUsername(csDTO.getUsername());
			s.getUser().setPassword(csDTO.getPassword());
			
			studentRepository.save(s);
			studentDTO = mapper.toDTO(s);
		}
		return studentDTO;
	}

	
	public void softDeleteStudent(Long id) {
		
		Optional<Student> studentOpt = studentRepository.findById(id);
		if(studentOpt.isPresent()) {
			Student s = studentOpt.get();
			s.getUser().setActive(false);
			studentRepository.save(s);
		}
	}

	public StudentDTO getStudent(Long id) {
		Optional<Student> studentOpt = studentRepository.findById(id);
		StudentDTO studentDTO= null;
		
        if (studentOpt.isPresent()) {
        	Student s = studentOpt.get();
        	studentDTO = mapper.toDTO(s);
            return studentDTO;
        } else {
            throw new RuntimeException("Student  ID " + id + " not found!");
        }
	}

	public StudentDTO getStudentByUserId(Long userId) {
		
		Optional<Student> optS = studentRepository.findByUserId(userId);
		StudentDTO studentDTO= null;
		if(optS.isPresent()) {
			Student s = optS.get();
			studentDTO = mapper.toDTO(s);
            return studentDTO;
		}
		return null;
	}

}
