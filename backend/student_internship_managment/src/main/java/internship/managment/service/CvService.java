package internship.managment.service;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import internship.managment.component.CvMapper;
import internship.managment.dto.CvDTO;
import internship.managment.model.Student;
import internship.managment.model.cv.CV;
import internship.managment.model.cv.CvEducation;
import internship.managment.model.cv.CvInterest;
import internship.managment.model.cv.CvInternship;
import internship.managment.model.cv.CvLanguage;
import internship.managment.model.cv.CvSkill;
import internship.managment.model.cv.CvWorkExperience;
import internship.managment.repository.StudentRepository;
import internship.managment.repository.cv.CvEducationRepository;
import internship.managment.repository.cv.CvInterestRepository;
import internship.managment.repository.cv.CvInternshipRepository;
import internship.managment.repository.cv.CvLanguageRepository;
import internship.managment.repository.cv.CvRepository;
import internship.managment.repository.cv.CvSkillRepository;
import internship.managment.repository.cv.CvWorkExperienceRepository;

@Service
public class CvService {
	
	@Autowired
	StudentRepository studentRepository;
	
	@Autowired
	CvRepository cvRepository;
	
	@Autowired
	CvMapper cvMapper;

	@Autowired
	CvInterestRepository interestRepository;

	@Autowired
	CvLanguageRepository languageRepository;

	@Autowired
	CvEducationRepository educationRepository;
	
	@Autowired
	CvInternshipRepository internshipRepository;
	
	@Autowired
	CvSkillRepository skillRepository;
	
	@Autowired
	CvWorkExperienceRepository experienceRepository;

	public boolean create(CvDTO cvDTO) {
		System.out.println("CvService: " + cvDTO);
		return false;
	}

	@Transactional
	public CvDTO saveCv(CvDTO cvDTO, MultipartFile photo) {
		System.out.println("CvService - saveCv: " + cvDTO);
		
		
		  // 1. Pronađi studenta 
		  Student student = studentRepository.findById(cvDTO.getStudentId()) .orElseThrow();
		  
		
		CV cv;

		if (cvRepository.existsByStudentId(cvDTO.getStudentId())) {

		    cv = cvRepository.findByStudentId(cvDTO.getStudentId());

		    // UPDATE
		    cv.setStudentAddress(cvDTO.getAddress());
		    cv.setStudentPhone(cvDTO.getPhone());

		    // obriši stare sekcije
		    if (photo != null && !photo.isEmpty()) {
		        deleteImage(cv.getImagePath());
		        String fileName = saveImage(photo);
		        cv.setImagePath(fileName);
		    }
		    
		    
		    educationRepository.deleteByCvId(cv.getId());
		    experienceRepository.deleteByCvId(cv.getId());
		    skillRepository.deleteByCvId(cv.getId());
		    internshipRepository.deleteByCvId(cv.getId());
		    languageRepository.deleteByCvId(cv.getId());
		    interestRepository.deleteByCvId(cv.getId());
		    

		} else {

		    // CREATE
		    cv = new CV();
		    cv.setStudent(student);
		    cv.setStudentAddress(cvDTO.getAddress());
		    cv.setStudentPhone(cvDTO.getPhone());
		}
		
		// 3. Sačuvaj sliku 
		  if(photo != null && !photo.isEmpty()){ 
			  String fileName = saveImage(photo);
			  cv.setImagePath(fileName); }
		
		  cvRepository.save(cv);
		
		// 4. Snimi sve sekcije 
		  saveEducations(cvDTO.getEducations(), cv);
		  saveExperiences(cvDTO.getExperiences(), cv);
		  //saveSkills(cvDTO.getSkills(),cv);
		  saveInternships(cvDTO.getInternships(), cv);
		  saveLanguages(cvDTO.getLanguages(),cv); 
		  saveInterests(cvDTO.getInterests(),cv);
		
		  
		  
		  List<CvEducation> list = cv.getEducations();
			list.forEach(System.out::println);
		  
		return cvMapper.toDTO(cv);		
	}

	private void saveInterests(List<CvInterest> interests, CV cv) {
		
		if(interests.size() == 0)
			return;
		interests.forEach( i -> {
			i.setCv(cv);
			interestRepository.save(i);
			
		});
		
	}

	private void saveLanguages(List<CvLanguage> languages, CV cv) {
		
		if( languages == null || languages.isEmpty())
			return;
		
		languages.forEach( l -> {
			l.setCv(cv);
			languageRepository.save(l);
		});
		
		
	}

	private void saveInternships(List<CvInternship> internships, CV cv) {
		
		if( internships == null  || internships.isEmpty())
			return;
		
		internships.forEach( i -> {
			i.setCv(cv);
			internshipRepository.save(i);
		});
		
	}

	private void saveSkills(List<CvSkill> skills, CV cv) {
		
		if(skills == null || skills.isEmpty())
			return;
		
		skills.forEach( s -> {
			s.setCv(cv);
			skillRepository.save(s);
		});
		
	}

	private void saveExperiences(List<CvWorkExperience> experiences, CV cv) {
		
		if(experiences == null || experiences.isEmpty())
			return;
		
		experiences.forEach( e-> {
			e.setCv(cv);
			experienceRepository.save(e);
		});
		
	}

	private void saveEducations(List<CvEducation> educations, CV cv) {
		
		if(educations == null || educations.isEmpty())
			return;		
		
		educations.forEach( e -> {
			e.setCv(cv);
			educationRepository.save(e);
		});
		
	}

	private String saveImage(MultipartFile file) {

	    try {
	        String original = file.getOriginalFilename();
	        String extension = original.substring(original.lastIndexOf("."));

	        String fileName = UUID.randomUUID().toString() + extension;

	        Path path = Paths.get("storage/cv-images/" + fileName);

	        Files.createDirectories(path.getParent());
	        Files.write(path, file.getBytes());

	        return fileName;

	    } catch (Exception e) {
	        throw new RuntimeException("Image upload failed");
	    }
	}
	
	private void deleteImage(String fileName) {
	    try {
	        if (fileName == null || fileName.isEmpty()) {
	            return;
	        }

	        Path path = Paths.get("storage/cv-images/" + fileName);

	        Files.deleteIfExists(path);

	    } catch (Exception e) {
	        throw new RuntimeException("Image delete failed");
	    }
	}

}
