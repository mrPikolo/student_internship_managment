package internship.managment.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import internship.managment.model.Company;

public interface CompanyRepository extends JpaRepository<Company, Long> {

}
