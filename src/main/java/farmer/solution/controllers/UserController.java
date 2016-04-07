package farmer.solution.controllers;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import farmer.solution.common.SessionIdentifierGenerator;
import farmer.solution.entities.User;
import farmer.solution.services.UserService;

@Controller
public class UserController {
	@Autowired
	private UserService userService;

	@RequestMapping(value = "", method = RequestMethod.GET)
	public String home() {
		return "index";
	}

	@RequestMapping(value = "/register", method = RequestMethod.POST)
	@ResponseBody
	public User register(@ModelAttribute User user, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			System.out.println("CAN NOT BIND USER: " + bindingResult.toString());
		}
		return userService.saveUser(user);
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	@ResponseBody
	public String login(@RequestParam("phoneNumber") String phoneNumber) {
		User user = userService.loadUserByPhoneNumber(phoneNumber);
		if (user != null) {
			String token = new SessionIdentifierGenerator().nextSessionId();
			user.setToken(token);
			userService.saveUser(user);
			return token;
		} else {
			return "NotFound";
		}

	}
}
