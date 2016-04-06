package farmer.solution.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import farmer.solution.entities.Question;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Long>{

}
