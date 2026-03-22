package internship.managment.repository.cv;

import org.springframework.data.jpa.repository.JpaRepository;

import internship.managment.model.cv.CvWorkExperience;

public interface CvWorkExperienceRepository extends JpaRepository<CvWorkExperience, Long> {

	void deleteByCvId(Long id);

}
