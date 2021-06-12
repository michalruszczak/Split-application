package pl.ruszczak.controller;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping
public class CustomController implements ErrorController{
	@GetMapping(path = "/error")
	public String handleError() {
		// This returns a JSON or XML with the users
		return "redirect:/event/list";
	}
}
