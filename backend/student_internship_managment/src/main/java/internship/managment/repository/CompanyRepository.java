package internship.managment.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import internship.managment.model.Company;
import java.util.Optional;

public interface CompanyRepository extends JpaRepository<Company, Long> {

	Optional<Company> findByName(String companyName);

}
