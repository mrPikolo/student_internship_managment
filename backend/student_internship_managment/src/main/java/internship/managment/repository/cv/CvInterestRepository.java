package internship.managment.repository.cv;

import org.springframework.data.jpa.repository.JpaRepository;

import internship.managment.model.cv.CvInterest;

public interface CvInterestRepository extends JpaRepository<CvInterest, Long> {

	void deleteByCvId(Long id);

}
