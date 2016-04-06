package farmer.solution.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import farmer.solution.entities.Question;
import farmer.solution.entities.User;
import farmer.solution.services.QuestionService;
import farmer.solution.services.UserService;

@Controller
@RequestMapping(value="/question")
public class QuestionController {
	@Autowired
	private UserService userService;
	@Autowired
	private QuestionService questionService;
	
	// create new question for specific user
	@RequestMapping(value="/create",method = RequestMethod.POST)
	@ResponseBody
	public Question createQuestion(@ModelAttribute Question question,@RequestParam("token") String token,BindingResult bindingResult){
		if (bindingResult.hasErrors()) {
			System.out.println(bindingResult.toString());
		}
		User user = userService.loadUserByToken(token);
		if (user!=null) {
			user.getQuestionList().add(question);
			userService.saveUser(user);
		}
		return question;
	}
	
	// loads all question if token != null
	// if token provided then load questions of specific user
	@RequestMapping(value="",method=RequestMethod.GET)
	@ResponseBody
	public List<Question> getAllQuestions(){
		return questionService.loadAllQuestions();
	}
	
	// loads question for specific user
	@RequestMapping(value="/{token}",method=RequestMethod.GET)
	@ResponseBody
	public List<Question> getQuestionsForSpecificUser(@PathVariable("token") String token){
			User user = userService.loadUserByToken(token);
			return user.getQuestionList();
	}
}
