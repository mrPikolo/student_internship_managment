package internship.managment.repository.cv;

import org.springframework.data.jpa.repository.JpaRepository;

import internship.managment.model.cv.CvLanguage;

public interface CvLanguageRepository extends JpaRepository<CvLanguage, Long> {

	void deleteByCvId(Long id);

}
