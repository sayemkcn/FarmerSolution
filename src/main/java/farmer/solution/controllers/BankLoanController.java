package farmer.solution.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import farmer.solution.entities.BankLoanApplication;
import farmer.solution.entities.User;
import farmer.solution.services.UserService;

@Controller
@RequestMapping(value = "/loan")
public class BankLoanController {

	@Autowired
	private UserService userService;

	@RequestMapping(value = "/create", method = RequestMethod.POST)
	@ResponseBody
	public BankLoanApplication postLoanRequest(@ModelAttribute BankLoanApplication bankLoanApplication,
			@RequestParam("token") String token, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			System.out.println(bindingResult.toString());
		}
		User user = userService.loadUserByToken(token);
		user.getBankLoanApplicationList().add(bankLoanApplication);
		userService.saveUser(user);
		return bankLoanApplication;

	}

}
