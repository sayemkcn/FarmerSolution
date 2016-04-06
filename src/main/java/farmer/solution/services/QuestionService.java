package farmer.solution.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import farmer.solution.entities.Question;
import farmer.solution.repositories.QuestionRepository;

@Service
public class QuestionService {
	@Autowired
	private QuestionRepository questionRepository;

	public List<Question> loadAllQuestions() {
		return questionRepository.findAll();
	}
}
