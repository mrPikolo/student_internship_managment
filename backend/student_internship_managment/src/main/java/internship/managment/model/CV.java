package internship.managment.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.OneToOne;

@Entity
public class CV {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	@OneToOne
	private Student student;
	
	@Lob
	private String education;
	
	@Lob
	private String experience;
	
	@Lob
	private String skills;
	
	@Lob
	private String interests;
	
	private String imagePath;
}
