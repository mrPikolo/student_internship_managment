package internship.managment.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import internship.managment.model.User;
import internship.managment.repository.UserRepository;

@Service
public class AuthService {
	
	@Autowired
	private UserRepository userRepository;
	

	public User authenticate(String username, String password) {
		
		Optional<User> userOpt = userRepository.findByUsername(username);

		/*
		 * if (userOpt.isEmpty()) { throw new RuntimeException("Korisnik ne postoji"); }
		 */

        User user = userOpt.get();

        // password chek
		
		  if (! password.equals( user.getPassword())){ 
			 // throw new  RuntimeException("Pogrešna lozinka");
			  user = null;
		  }
		 

        return user;
	}
	
	public User createUser(User user) {
        if (userRepository.existsByUsername(user.getUsername())) {
            throw new RuntimeException("Username already exists");
        }
        
       // user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

}
