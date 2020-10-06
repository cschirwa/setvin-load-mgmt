package za.co.setvin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TruckController {

	@GetMapping("/trucks")
	public String showTrucks() {
		return "trucks";
	}
	
	@GetMapping("truck_add")
	public String addTruck() {
		return "truck_add";
	}
	
	@GetMapping("truck_view")
	public String viewTruck() {
		return "truck_view";
	}
}
