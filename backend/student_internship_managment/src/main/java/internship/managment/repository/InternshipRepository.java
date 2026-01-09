package internship.managment.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import internship.managment.model.Internship;

public interface InternshipRepository extends JpaRepository<Internship, Long> {

	@Query("select i from Internship i where i.active = true")
	List<Internship> findAllActive();

}
