package farmer.solution.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import farmer.solution.entities.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	User findByPhoneNumber(String phoneNumber);
	User findByToken(String token);
}
