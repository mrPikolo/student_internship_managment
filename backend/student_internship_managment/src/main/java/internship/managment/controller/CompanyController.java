package internship.managment.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import internship.managment.dto.LoginRequestDTO;
import internship.managment.model.User;
import internship.managment.service.AuthService;
import internship.managment.service.CompanyService;

@RestController
@RequestMapping("/api/companies")
@CrossOrigin(origins = "http://localhost:8085")
public class CompanyController {
	
	@Autowired
	private AuthService authService;
	
	@Autowired
	private CompanyService companyService;
	
	@PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequestDTO request) {

        User user = authService.authenticate(request.getUsername(), request.getPassword());

        if(user == null){
            return ResponseEntity.badRequest()
                    .body(Map.of("error", "Invalid username or password"));
        }

        if(!"COMPANY".equals(user.getRole().toString())){
            return ResponseEntity.badRequest()
                    .body(Map.of("error", "You do not have access to this application."));
        }

        boolean isActive = authService.isActive(request.getUsername());
        if(!isActive){
            return ResponseEntity.badRequest()
                    .body(Map.of("error", "Account is inactive."));
        }

        
        CompanyResponseDTO response = new CompanyResponseDTO();
        response.setId(user.getId());
        response.setName(user.getUsername());        
        response.setDescription("...");            
        response.setAccountStatus(isActive);

        Map<String,Object> map = new HashMap<>();
        map.put("user", response);

        return ResponseEntity.ok(map);
    }
	
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
	
	@GetMapping("/{userId}")
	public CompanyResponseDTO getByUserId(@PathVariable Long userId) {
		return companyService.getByUser(userId);
	}
	
	@GetMapping("/name/{userId}")
	public String getCompanyNameByUser(@PathVariable Long userId) {
		return companyService.getCompanyNameByUser(userId);
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
