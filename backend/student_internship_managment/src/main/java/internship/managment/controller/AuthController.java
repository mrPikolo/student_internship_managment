package internship.managment.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import internship.managment.dto.LoginRequestDTO;
import internship.managment.dto.LoginResponseDTO;
import internship.managment.model.User;
import internship.managment.service.AuthService;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "http://localhost:4200")
public class AuthController {
	
	@Autowired
	private AuthService authService;
	
	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody LoginRequestDTO request) {	
				
		
			User user = authService.authenticate(
					request.getUsername(),
					request.getPassword()
					);

            Map<String, Object> response = new HashMap<>();
            
            if (user != null && authService.isActive(request.getUsername())) {
            	LoginResponseDTO loginResponse = new LoginResponseDTO();
            	loginResponse.setId(user.getId());
            	loginResponse.setUsername(user.getUsername());
            	loginResponse.setRole(user.getRole().toString());
            	
            	response.put("user", loginResponse);
            	
            	return ResponseEntity.ok(response);
            }
            else if ( !authService.isActive(request.getUsername())) {
            	return ResponseEntity.badRequest()
                        .body(Map.of("error", "User inactive"));
            }
            else {
            	return ResponseEntity.badRequest()
                        .body(Map.of("error", "Invalid username or password"));
            }              
	}

}
