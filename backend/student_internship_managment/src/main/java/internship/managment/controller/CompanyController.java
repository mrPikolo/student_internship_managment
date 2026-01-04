package internship.managment.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import internship.managment.dto.CompanyDTO;
import internship.managment.dto.CompanyResponseDTO;
import internship.managment.service.CompanyService;

@RestController
@RequestMapping("/api/companys")
@CrossOrigin(origins = "http://localhost:8085")
public class CompanyController {
	
	@Autowired
	private CompanyService companyService;
	
	@PostMapping
	public ResponseEntity<?> create(@RequestBody CompanyDTO companyDTO) {
		
		CompanyDTO response = companyService.create(companyDTO);
		if (response != null)
			return ResponseEntity.status(HttpStatus.CREATED).body(response);
		else
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Username exists!");
	}
	
	@GetMapping
	public List<CompanyResponseDTO> getAll() {
		return companyService.getAll();
	}
	
	@PostMapping("/{id}/activate")
    public ResponseEntity<CompanyResponseDTO> activateCompany(@PathVariable Long id) {
        return ResponseEntity.ok(companyService.activateCompany(id));
    }

    @PostMapping("/{id}/deactivate")
    public ResponseEntity<CompanyResponseDTO> deactivateCompany(@PathVariable Long id) {
        return ResponseEntity.ok(companyService.deactivateCompany(id));
    }
	

}
