package internship.managment.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import internship.managment.model.User;

public interface UserRepository extends JpaRepository<User, Long>{

	Optional<User> findByUsername(String username);
	Optional<User> findByUsernameAndActiveTrue(String username);
	boolean existsByUsername(String username);
    boolean existsByUsernameAndActiveTrue(String username);

}
