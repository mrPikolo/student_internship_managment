package internship.managment.component;

import org.springframework.stereotype.Component;

import internship.managment.dto.CvDTO;
import internship.managment.model.cv.CV;

@Component
public class CvMapper {
	
	public CvDTO toDTO(CV cv) {
		
		CvDTO dto = new CvDTO();
		dto.setStudentId(cv.getStudent().getId());
		dto.setPhone(cv.getStudentPhone());
		dto.setAddress(cv.getStudentAddress());
		
		dto.setImagePath(cv.getImagePath());
		
		dto.setEducations(cv.getEducations());
		dto.setExperiences(cv.getExperiences());
		dto.setInternships(cv.getInternships());
		dto.setInterests(cv.getInterests());
		dto.setSkills(cv.getSkills());
		dto.setLanguages(cv.getLanguages());
		
		return dto;
	}

}
