package za.co.setvin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import za.co.setvin.entity.User;

@Controller
public class HomeController {
	
	@GetMapping("/login")
	public String showLogin() {
		return "login";
	}
	
	@PostMapping("/login")
	public String performLogin(@ModelAttribute User user) {
		return "redirect:/index";
	}
	
	@GetMapping("/dashboard")
	public String showDash() {
		return "index";
	}

	@GetMapping("/index")
	public String showIndex() {
		return "index";
	}
	
	@GetMapping("/logout")
	public String logout() {
		return "redirecr:/login?logout";
	}
}
