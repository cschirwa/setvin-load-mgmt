package za.co.setvin.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import za.co.setvin.entity.Customer;
import za.co.setvin.entity.Driver;
import za.co.setvin.entity.Load;
import za.co.setvin.entity.Status;
import za.co.setvin.entity.Trailer;
import za.co.setvin.service.CustomerService;
import za.co.setvin.service.DriverService;
import za.co.setvin.service.LoadService;
import za.co.setvin.service.StatusService;
import za.co.setvin.service.TrailerService;

@Controller
public class LoadController {

	@Autowired
	private LoadService loadService;
	
	@Autowired
	private CustomerService customerService;
	
	@Autowired
	private TrailerService trailerService;
	
	@Autowired
	private StatusService statusService;
	
	@Autowired
	private DriverService driverService;
	
	@GetMapping("/loads")
	public String showLoads(Model model) {
		List<Load> loads = loadService.getAll();
		model.addAttribute("loadList", loads);
		return "loads";
	}
	
	@GetMapping("/load_view/{loadId}")
	public String viewLoad(@PathVariable String loadId, 
			Model model) {
		model.addAttribute("load", loadService.get(Long.parseLong(loadId)));
		model.addAttribute("customer", loadService.get(Long.parseLong(loadId)).getCustomer());
		model.addAttribute("subTotal", loadService.getSubTotal(loadService.get(Long.parseLong(loadId))));
		model.addAttribute("vat", loadService.getVat(loadService.get(Long.parseLong(loadId))));
		model.addAttribute("total", loadService.getTotal(loadService.get(Long.parseLong(loadId))));
		return "load_view";
	}
	
	@GetMapping("/load_add")
	public String viewAddLoad(Model model) {
		List<Customer> customers = customerService.getAll();
		List<Trailer> trailers = trailerService.findAllIfAvailable();
		List<Status> statuses = statusService.getAll();
		List<Driver> drivers = driverService.findAvailableDrivers();
		model.addAttribute("load", new Load());
		model.addAttribute("customerList", customers);
		model.addAttribute("trailerList", trailers);
		model.addAttribute("statusList", statuses);
		model.addAttribute("driverList", drivers);
		return "load_add";
	}
	
	@PostMapping("/load_add")
	public String addLoad(@ModelAttribute Load load, 
			BindingResult result, 
			RedirectAttributes redirectAttributes) {
		loadService.add(load);
			
		if(result.hasErrors()) {
			redirectAttributes.addFlashAttribute("message", "Failed");
			redirectAttributes.addFlashAttribute("alertClass", "alert-danger");
			return "load_add";
		}
		redirectAttributes.addFlashAttribute("message", "Success");
		redirectAttributes.addFlashAttribute("alertClass", "alert-success");
		return "loads";
	}
	
	@GetMapping("/load_edit/{loadId}")
	public String viewEditLoad(@PathVariable String loadId, Model model) {
		List<Customer> customers = customerService.getAll();
		List<Trailer> trailers = trailerService.findAllIfAvailable();
		List<Status> statuses = statusService.getAll();
		List<Driver> drivers = driverService.findAvailableDrivers();
		Load load = loadService.get(Long.valueOf(loadId));
		model.addAttribute("load", load);
		model.addAttribute("customerList", customers);
		model.addAttribute("trailerList", trailers);
		model.addAttribute("statusList", statuses);
		model.addAttribute("driverList", drivers);
		return "load_edit";
	}
	
	@PostMapping("/load_edit/{loadId}")
	public String postEditLoad(@PathVariable String loadId, 
			@ModelAttribute Load load,
			BindingResult result,
			RedirectAttributes attributes) {
		if(!result.hasErrors()) {
			loadService.add(load);
			attributes.addFlashAttribute("message", "Success");
			attributes.addFlashAttribute("alertClass", "alert-success");
			return "redirect:loads";
		}
		attributes.addFlashAttribute("message", "Failed");
		attributes.addFlashAttribute("alertClass", "alert-danger");
		return "load_edit";
		
	}
	
}
