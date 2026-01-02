package internship.managment.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import internship.managment.model.Student;
import internship.managment.model.User;

public interface StudentRepository extends JpaRepository<Student, Long>{

}
