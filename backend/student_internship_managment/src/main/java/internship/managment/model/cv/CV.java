package internship.managment.model.cv;

import java.util.List;

import internship.managment.model.Student;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;

@Entity
public class CV {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	@OneToOne
	@JoinColumn(name="student_id")
	private Student student;
	
	@OneToMany(mappedBy = "cv")
	private List<CvEducation> educations;
	
	@OneToMany(mappedBy = "cv")
	private List<CvWorkExperience> experiences;
	
	@OneToMany(mappedBy = "cv")
	private List<CvInternship> internships;
	
	@OneToMany(mappedBy = "cv")
	private List<CvSkill> skills;
	
	@OneToMany(mappedBy = "cv")
	private List<CvInterest> interests;
	
	private String imagePath;

}
