package internship.managment.repository.cv;

import org.springframework.data.jpa.repository.JpaRepository;

import internship.managment.model.cv.CvEducation;

public interface CvEducationRepository extends JpaRepository<CvEducation, Long> {

	void deleteByCvId(Long id);

}
