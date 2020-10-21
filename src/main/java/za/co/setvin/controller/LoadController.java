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
import za.co.setvin.entity.Truck;
import za.co.setvin.service.CustomerService;
import za.co.setvin.service.DriverService;
import za.co.setvin.service.LoadService;
import za.co.setvin.service.StatusService;
import za.co.setvin.service.TrailerService;
import za.co.setvin.service.TruckService;

@Controller
public class LoadController {

	@Autowired
	private LoadService loadService;
	
	@Autowired
	private CustomerService customerService;
	
	@Autowired
	private StatusService statusService;
	
	@Autowired
	private DriverService driverService;
	
	@Autowired
	private TruckService truckService;
	
	@GetMapping("/loads")
	public String showLoads(Model model) {
		List<Load> loads = loadService.getAll();
		model.addAttribute("loadList", loads);
		return "loads";
	}
	
	@GetMapping("/load/view/{loadId}")
	public String viewLoad(@PathVariable String loadId, 
			Model model) {
		Customer customer = customerService.findByName(loadService.get(Long.parseLong(loadId)).getCustomer());
		model.addAttribute("load", loadService.get(Long.parseLong(loadId)));
		model.addAttribute("customer", customer);
		model.addAttribute("subTotal", loadService.getSubTotal(loadService.get(Long.parseLong(loadId))));
		model.addAttribute("vat", loadService.getVat(loadService.get(Long.parseLong(loadId))));
		model.addAttribute("total", loadService.getTotal(loadService.get(Long.parseLong(loadId))));
		return "load_view";
	}
	
	@GetMapping("/load/add")
	public String viewAddLoad(Model model) {
		List<Customer> customers = customerService.getAll();
		List<Status> statuses = statusService.getAll();
		List<Truck> trucks = truckService.getAll();
		List<Driver> drivers = driverService.getAll();
		model.addAttribute("load", new Load());
		model.addAttribute("customerList", customers);
		model.addAttribute("truckList", trucks);
		model.addAttribute("statusList", statuses);
		model.addAttribute("driverList", drivers);
		return "load_add";
	}
	
	@PostMapping("/load/add")
	public String addLoad(@ModelAttribute Load load, 
			BindingResult result, 
			RedirectAttributes redirectAttributes) {
			
		if(result.hasErrors()) {
			redirectAttributes.addFlashAttribute("message", "Failed");
			redirectAttributes.addFlashAttribute("alertClass", "alert-danger");
			return "load_add";
		}
		loadService.add(load);
		redirectAttributes.addFlashAttribute("message", "Success");
		redirectAttributes.addFlashAttribute("alertClass", "alert-success");
		return "redirect:/loads";
	}
	
	@GetMapping("/load/edit/{loadId}")
	public String viewEditLoad(@PathVariable String loadId, Model model) {
		Load load = loadService.get(Long.valueOf(loadId));
		List<Customer> customers = customerService.getAll();
		List<Status> statuses = statusService.getAll();
		List<Driver> drivers = driverService.getAll();
		List<Truck> trucks = truckService.getAll();
		model.addAttribute("load", load);
		model.addAttribute("customerList", customers);
		model.addAttribute("statusList", statuses);
		model.addAttribute("driverList", drivers);
		model.addAttribute("truckList", trucks);
		return "load_edit";
	}
	
	@PostMapping("/load/edit/{loadId}")
	public String postEditLoad(@PathVariable String loadId, 
			@ModelAttribute Load load,
			BindingResult result,
			RedirectAttributes attributes) {
		if(!result.hasErrors()) {
			loadService.amend(Long.parseLong(loadId), load);
			attributes.addFlashAttribute("message", "Success");
			attributes.addFlashAttribute("alertClass", "alert-success");
			return "redirect:/loads";
		}
		attributes.addFlashAttribute("message", "Failed");
		attributes.addFlashAttribute("alertClass", "alert-danger");
		return "load_edit";
		
	}
	
}
