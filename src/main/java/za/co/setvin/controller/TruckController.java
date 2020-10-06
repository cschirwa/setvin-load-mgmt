package za.co.setvin.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import za.co.setvin.entity.Truck;import za.co.setvin.service.DriverService;
import za.co.setvin.service.TruckService;

@Controller
public class TruckController {
	
	private static final Logger log = LoggerFactory.getLogger(TruckController.class);
	
	@Autowired
	private TruckService truckService;
	
	@Autowired 
	private DriverService driverService;

	@GetMapping("/trucks")
	public String showTrucks(Model model) {
		List<Truck> trucks = truckService.getAll();
		model.addAttribute("truckList", trucks);
		return "trucks";
	}
	
	@GetMapping("/truck_add")
	public String addTruck(Model model) {
		model.addAttribute("truck", new Truck());
		model.addAttribute("driverList", driverService.getAll());
		return "truck_add";
	}
	
	@GetMapping("/truck_edit/{truckId}")
	public String viewEditTruck(@PathVariable("truckId") String truckId, Model model) {
		Truck truck = truckService.findById(Long.parseLong(truckId));
		model.addAttribute("truck", truck);
		model.addAttribute("driverList", driverService.getAll());
		return "truck_edit";
	}
	
	@GetMapping("/truck_view/{truckId}")
	public String viewTruck(@PathVariable("truckId") String truckId, Model model) {
		Truck truck = truckService.findById(Long.parseLong(truckId));
		model.addAttribute("truck", truck);
		return "truck_view";
	}
	
	@PostMapping("/truck_add")
	public String postAddTruck(@ModelAttribute Truck truck,
			BindingResult result, 
			RedirectAttributes attributes) {
		if(!result.hasErrors()) {
			truckService.add(truck);
			attributes.addFlashAttribute("message", "Success");
			attributes.addFlashAttribute("alertClass", "alert-success");
			log.info("Truck %s added successfully to database", truck.getRegistration());
			return "redirect:/trucks";
		}
		attributes.addFlashAttribute("message", "Failed");
		attributes.addFlashAttribute("alertClass", "alert-danger");
		log.error("Truck not saved to database");
		return "truck_add";
	}
	
	
	@PostMapping("/truck_edit/{truckId}")
	public String postEditTruck(@PathVariable("truckId") String truckId,
			@ModelAttribute Truck truck,
			BindingResult result, 
			RedirectAttributes attributes) {
		if(!result.hasErrors()) {
			truckService.amend(Long.parseLong(truckId), truck);
			attributes.addFlashAttribute("message", "Success");
			attributes.addFlashAttribute("alertClass", "alert-success");
			log.info("Truck %s saved successfully to database", truck.getRegistration());
			return "redirect:/trucks";
		}
		attributes.addFlashAttribute("message", "Failed");
		attributes.addFlashAttribute("alertClass", "alert-danger");
		log.error("Truck not saved to database");
		return "truck_edit";
	}
}
