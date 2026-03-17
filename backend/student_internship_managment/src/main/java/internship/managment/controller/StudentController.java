package internship.managment.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDate;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import internship.managment.dto.CreateStudentDTO;
import internship.managment.dto.StudentDTO;
import internship.managment.service.StudentService;

@RestController
@RequestMapping("/api/students")
@CrossOrigin(origins = "http://localhost:4200")
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
	
	@GetMapping("/{id}")
	public StudentDTO getStudent(@PathVariable Long id) {
		return studentService.getStudent(id);
	}
	
	@GetMapping("/{userId}/user")
	public StudentDTO getStudentByUserIdgetStudent(@PathVariable Long userId) {
		return studentService.getStudentByUserId(userId);
	}
	
	@PutMapping("/{id}")
	public StudentDTO updateStudent(@PathVariable Long id,@RequestBody CreateStudentDTO studentDTO) {
		return studentService.updateStudent(id,studentDTO);
	}
	
	@DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
		studentService.softDeleteStudent(id);
    }
	
	@PostMapping("/upload")
    public ResponseEntity<String> uploadCSV(@RequestParam("file") MultipartFile file) {

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(file.getInputStream()))) {

            String line;
            reader.readLine(); // skip header

            while ((line = reader.readLine()) != null) {
            	
                String[] data = line.split(",");
                
                CreateStudentDTO dto = new CreateStudentDTO();
                dto.setFirstName(data[0]);
                dto.setLastName(data[1]);
                dto.setEmail(data[2]);
                dto.setBirthDate(LocalDate.parse(data[3]));
                dto.setUsername(data[4]);
                dto.setPassword(data[5]);
                
                studentService.create(dto);
            }
            
            return ResponseEntity.ok("CSV upload sucsess");
            
        } catch (IOException e) {
        	return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body("Error processing CSV file");
		}
        
	}
}
