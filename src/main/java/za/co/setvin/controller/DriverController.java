package za.co.setvin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import za.co.setvin.entity.Driver;
import za.co.setvin.service.DriverService;

@Controller
public class DriverController {
	
	@Autowired 
	private DriverService driverService;

	@GetMapping("/drivers")
	public String showDrivers(Model model) {
		model.addAttribute("driverList", driverService.getAll());
		return "drivers";
	}
	
	@GetMapping("driver_add")
	public String addDriver(Model model) {
		model.addAttribute("driver", new Driver());
		return "driver_add";
	}
	
	@GetMapping("driver_view/{id}")
	public String viewDriver(@PathVariable String id, Model model) {
		model.addAttribute("driver", driverService.findById(Long.valueOf(id)));
		return "driver_view";
	}
	
	@GetMapping("driver_edit/{id}")
	public String viewEditDriver(@PathVariable String id, Model model) {
		model.addAttribute("driver", driverService.findById(Long.valueOf(id)));
		return "driver_edit";
	}
}
