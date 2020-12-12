package za.co.setvin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import za.co.setvin.entity.Driver;
import za.co.setvin.service.CountryService;
import za.co.setvin.service.DriverService;

@Controller
public class DriverController {
	
	@Autowired 
	private DriverService driverService;
	
	@Autowired
	private CountryService countryService;

	@GetMapping("/drivers")
	public String showDrivers(Model model) {
		model.addAttribute("driverList", driverService.getAll());
		return "drivers";
	}
	
	@GetMapping("/driver/view/{id}")
	public String viewDriver(@PathVariable String id, Model model) {
		model.addAttribute("driver", driverService.findById(Long.valueOf(id)));
		return "driver_view";
	}
	
	@GetMapping("/driver/add")
	public String addDriver(Model model) {
		model.addAttribute("driver", new Driver());
		model.addAttribute("countryList", countryService.getAll());
		return "driver_add";
	}
	
	@PostMapping("/driver/add")
	public String postAddDriver(@ModelAttribute Driver driver,
			BindingResult result,
			RedirectAttributes attributes) {
		if(!result.hasErrors()) {
			driverService.add(driver);
			attributes.addFlashAttribute("message", "Success");
			attributes.addFlashAttribute("alertClass", "alert-success");
			return "redirect:/drivers";
		}
		attributes.addFlashAttribute("message", "Failed");
		attributes.addFlashAttribute("alertClass", "alert-danger");
		return "driver_add";
	}
	
	@GetMapping("/driver/edit/{id}")
	public String viewEditDriver(@PathVariable String id, Model model) {
		model.addAttribute("driver", driverService.findById(Long.valueOf(id)));
		model.addAttribute("countryList", countryService.getAll());
		return "driver_edit";
	}
	
	@PostMapping("/driver/edit/{id}")
	public String postEditDriver(
			@PathVariable String id,
			@ModelAttribute Driver driver,
			BindingResult result,
			RedirectAttributes attributes) {
		if(!result.hasErrors()) {
			driver.setId(Long.parseLong(id));
			driverService.add(driver);
			attributes.addFlashAttribute("message", "Success");
			attributes.addFlashAttribute("alertClass", "alert-success");
			return "redirect:/drivers";
		}
		attributes.addFlashAttribute("message", "Failed");
		attributes.addFlashAttribute("alertClass", "alert-danger");
		return "driver_add";
	}
	
	@RequestMapping("/driver/delete/{id}")
	public String deleteDriver(@PathVariable String id) {
		driverService.delete(Long.parseLong(id));
		return "redirect:/drivers";
	}
}
