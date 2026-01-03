package internship.managment.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
	
	@GetMapping
	public List<StudentDTO> getAll() {
		return studentService.getAll();
	}
	
	@PutMapping("/{id}")
	public StudentDTO updateStudent(@PathVariable Long id,@RequestBody CreateStudentDTO studentDTO) {
		return studentService.updateStudent(id,studentDTO);
	}
	
	@PatchMapping("/{id}/delete")
    public void delete(@PathVariable Long id) {
		studentService.softDeleteStudent(id);
    }
}
