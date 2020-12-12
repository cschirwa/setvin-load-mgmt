package za.co.setvin.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import za.co.setvin.entity.User;
import za.co.setvin.service.UserService;

@Controller
public class ProfileController {
	
	@Autowired
	private UserService userService;
	
	@GetMapping("/profile")
	public String showProfile(Model model, Principal principal) {
		model.addAttribute("user", userService.findByUsername(principal.getName()));
		model.addAttribute("profile", userService.findByUsername(principal.getName()));
		return "profile";
	}
	
	@PostMapping("/profile")
	public String updateProfile(Principal principal,
			@ModelAttribute User user,
			BindingResult result,
			RedirectAttributes attributes) {
		if(!result.hasErrors()) {
			User dbUser = userService.findByUsername(principal.getName());
			dbUser.setEmail(user.getEmail());
			dbUser.setFirstname(user.getFirstname());
			dbUser.setLastname(user.getLastname());
			dbUser.setLocation(user.getLocation());
			dbUser.setPhone(user.getPhone());
			dbUser.setPhone2(user.getPhone2());
			userService.save(dbUser);
			attributes.addFlashAttribute("message", "Success");
			attributes.addFlashAttribute("alertClass", "alert-success");
			return "redirect:/index";
		}
		attributes.addFlashAttribute("message", "Failed");
		attributes.addFlashAttribute("alertClass", "alert-danger");
		return "/profile";
		
	}
	
	@GetMapping("/profile/add")
	public String addProfile() {
		return "profile_add";
	}
	
	@GetMapping("/change_password")
	public String viewChangeProfile() {
		return "change_password";
	}

}
