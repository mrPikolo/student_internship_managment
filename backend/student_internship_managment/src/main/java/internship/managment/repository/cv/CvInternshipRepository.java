package internship.managment.repository.cv;

import org.springframework.data.jpa.repository.JpaRepository;

import internship.managment.model.cv.CvInternship;

public interface CvInternshipRepository extends JpaRepository<CvInternship, Long> {

	void deleteByCvId(Long id);

}
