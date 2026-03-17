package internship.managment.dto;

import java.util.List;

import internship.managment.model.cv.CvEducation;
import internship.managment.model.cv.CvInterest;
import internship.managment.model.cv.CvInternship;
import internship.managment.model.cv.CvSkill;
import internship.managment.model.cv.CvWorkExperience;

public class CvDTO {
	
	// Personal info
	private Long studentId;
	private String phone;
	private String address;
	private String imagePath;
	
	// cv
	private List<CvEducation> educations;
	private List<CvWorkExperience> experiences;
	private List<CvInternship> internships;
	private List<CvSkill> skills;
	private List<CvInterest> interests;
	
	public Long getStudentId() {
		return studentId;
	}
	public void setStudentId(Long studentId) {
		this.studentId = studentId;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getImagePath() {
		return imagePath;
	}
	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
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
	@Override
	public String toString() {
		return "CvDTO [studentId=" + studentId + ", phone=" + phone + ", address=" + address + ", imagePath="
				+ imagePath + ", educations=" + educations + ", experiences=" + experiences + ", internships="
				+ internships + ", skills=" + skills + ", interests=" + interests + "]";
	}
	
	

}
