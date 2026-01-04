package internship.managment.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import internship.managment.model.Student;
import internship.managment.model.User;

public interface StudentRepository extends JpaRepository<Student, Long>{
	
	@Query("select s from Student s where s.user.active = true")
	List<Student> findAllActive();

}
