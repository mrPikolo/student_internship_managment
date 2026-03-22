package internship.managment.repository.cv;

import org.springframework.data.jpa.repository.JpaRepository;

import internship.managment.model.cv.CvSkill;

public interface CvSkillRepository extends JpaRepository<CvSkill, Long> {

	void deleteByCvId(Long id);

}
