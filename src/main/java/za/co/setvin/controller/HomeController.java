package za.co.setvin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
	
	@GetMapping("/login")
	public String showLogin() {
		return "login";
	}
	
	@GetMapping("/dashboard")
	public String showDash() {
		return "index";
	}

	@GetMapping("/index")
	public String showIndex() {
		return "index";
	}
}
