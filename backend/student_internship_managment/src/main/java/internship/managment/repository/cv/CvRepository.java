package internship.managment.repository.cv;

import org.springframework.data.jpa.repository.JpaRepository;

import internship.managment.model.cv.CV;

public interface CvRepository extends JpaRepository<CV, Long> {

	CV findByStudentId(Long studentId);

	boolean existsByStudentId(Long studentId);
	
	

}
