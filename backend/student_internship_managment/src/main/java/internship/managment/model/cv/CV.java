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
	
	@OneToMany(mappedBy = "cv")
	private List<CvLanguage> languages;
	
	private String imagePath;
	private String studentPhone;
	private String studentAddress;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Student getStudent() {
		return student;
	}
	public void setStudent(Student student) {
		this.student = student;
	}
	public List<CvEducation> getEducations() {
		return educations;
	}
	public void setEducations(List<CvEducation> educations) {
		this.educations = educations;
	}
	public List<CvWorkExperience> getExperiences() {
		return experiences;
	}
	public void setExperiences(List<CvWorkExperience> experiences) {
		this.experiences = experiences;
	}
	public List<CvInternship> getInternships() {
		return internships;
	}
	public void setInternships(List<CvInternship> internships) {
		this.internships = internships;
	}
	public List<CvSkill> getSkills() {
		return skills;
	}
	public void setSkills(List<CvSkill> skills) {
		this.skills = skills;
	}
	public List<CvInterest> getInterests() {
		return interests;
	}
	public void setInterests(List<CvInterest> interests) {
		this.interests = interests;
	}
	public String getImagePath() {
		return imagePath;
	}
	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}
	public String getStudentPhone() {
		return studentPhone;
	}
	public void setStudentPhone(String studentPhone) {
		this.studentPhone = studentPhone;
	}
	public String getStudentAddress() {
		return studentAddress;
	}
	public void setStudentAddress(String studentAddress) {
		this.studentAddress = studentAddress;
	}
	public List<CvLanguage> getLanguages() {
		return languages;
	}
	public void setLanguages(List<CvLanguage> languages) {
		this.languages = languages;
	}
}
