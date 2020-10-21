package za.co.setvin.controller;

import org.springframework.web.bind.annotation.GetMapping;

public class ProfileController {
	
	@GetMapping("/profile")
	public String showProfile() {
		return "profile";
	}
	
	@GetMapping("/profile_add")
	public String addProfile() {
		return "profile_add";
	}
	
	@GetMapping("/profile_view")
	public String viewProfile() {
		return "profile_view";
	}
	
	@GetMapping("/change_password")
	public String vieChangeProfile() {
		return "change_password";
	}

}
