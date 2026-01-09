package internship.managment.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import internship.managment.dto.InternshipDTO;
import internship.managment.dto.UpdateInternshipDTO;
import internship.managment.service.InternshipService;

@RestController
@RequestMapping("/api/internships")
@CrossOrigin(origins = "http://localhost:8085")
public class InternshipController {

	@Autowired
	InternshipService internshipService;
	
	@GetMapping
	public List<InternshipDTO> getAll() {
		
		return internshipService.getAll();
	}
	
	@PostMapping
	public ResponseEntity<?> create(@RequestBody InternshipDTO internshipDTO ) {
		
		InternshipDTO response = internshipService.create(internshipDTO);
		if (response != null)
			return ResponseEntity.status(HttpStatus.CREATED).body(response);
		else
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Company not exists!");
	}
	
	@PutMapping("/{id}")
	public InternshipDTO update(@PathVariable Long id ,@RequestBody UpdateInternshipDTO uiDTO) {
		return internshipService.update(id,uiDTO);
	}
	
	@PatchMapping("/{id}/delete")
    public void delete(@PathVariable Long id) {
		internshipService.softDeleteStudent(id);
    }
}
