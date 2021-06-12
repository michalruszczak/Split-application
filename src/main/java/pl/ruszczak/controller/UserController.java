package pl.ruszczak.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import pl.ruszczak.model.User;
import pl.ruszczak.repository.UserRepository;

@Controller // This means that this class is a Controller
@RequestMapping(path = "/user") // This means URL's start with /demo (after Application path)
public class UserController {
	@Autowired
	private UserRepository userRepository;

	@Autowired
	BCryptPasswordEncoder encoder;

	@GetMapping(path = "/register")
	public String addNewUserGet(Model model){
		model.addAttribute("user", new User());
		return "userRegistrationForm";
	}

	@PostMapping(path = "/register") // Map ONLY POST Requests
	public String addNewUser(Model model, @ModelAttribute("user") User user) {
		// @ResponseBody means the returned String is the response, not a view name
		// @RequestParam means it is a parameter from the GET or POST request

		user.setPassword(encoder.encode(user.getPassword()));
		userRepository.save(user);
		return "redirect:/user/register";
	}

	@GetMapping(path = "/all")
	public @ResponseBody Iterable<User> getAllUsers() {
		// This returns a JSON or XML with the users
		return userRepository.findAll();
	}
}
