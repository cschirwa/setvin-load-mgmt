package za.co.setvin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DriverController {

	@GetMapping("/drivers")
	public String showDrivers() {
		return "drivers";
	}
	
	@GetMapping("driver_add")
	public String addDriver() {
		return "driver_add";
	}
	
	@GetMapping("driver_view")
	public String viewDriver() {
		return "driver_view";
	}
}
