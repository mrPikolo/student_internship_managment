package internship.managment.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import internship.managment.dto.CreateStudentDTO;
import internship.managment.dto.StudentDTO;
import internship.managment.service.StudentService;

@RestController
@RequestMapping("/api/students")
@CrossOrigin(origins = "http://localhost:8085")
public class StudentController {
	
	@Autowired
	StudentService studentService;
	
	@PostMapping
	public ResponseEntity<StudentDTO> createStudent( @RequestBody CreateStudentDTO dto) {
		
		StudentDTO student = studentService.create(dto);
		return ResponseEntity.status(HttpStatus.CREATED).body(student);
	}
}
