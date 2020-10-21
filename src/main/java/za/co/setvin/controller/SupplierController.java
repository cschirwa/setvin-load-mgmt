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

import za.co.setvin.entity.Supplier;
import za.co.setvin.service.CountryService;
import za.co.setvin.service.SupplierService;

@Controller
public class SupplierController {

	@Autowired
	private SupplierService supplierService;
	
	@Autowired CountryService countryService;
	
	@GetMapping("/suppliers")
	public String showSuppliers(Model model) {
		model.addAttribute("supplierList", supplierService.getAll());
		return "suppliers";
	}
	
	@GetMapping("/supplier/add")
	public String addSupplier(Model model) {
		model.addAttribute("countryList", countryService.getAll());
		model.addAttribute("supplier", new Supplier());
		return "supplier_add";
	}
	
	@GetMapping("/supplier/view/{id}")
	public String viewSupplier(@PathVariable String id,
			Model model) {
		model.addAttribute("supplier", supplierService.findById(Long.parseLong(id)));
		return "supplier_view";
	}
	
	@GetMapping("/supplier/edit/{id}")
	public String viewEditSupplier(@PathVariable String id,
			Model model) {
		model.addAttribute("supplier", supplierService.findById(Long.parseLong(id)));
		return "supplier_edit";
	}
	
	@PostMapping("/supplier/edit/{id}")
	public String postEditSupplier(@PathVariable String id,
			@ModelAttribute Supplier supplier,
			BindingResult result,
			RedirectAttributes attributes
			) {
		if(!result.hasErrors()) {
			supplierService.amend(Long.parseLong(id), supplier);
			attributes.addFlashAttribute("message", "Success");
			attributes.addFlashAttribute("alertClass", "alert-success");
			return "redirect:/suppliers";
		}
		attributes.addFlashAttribute("message", "Failed");
		attributes.addFlashAttribute("alertClass", "alert-danger");
		return "supplier_edit";
	}
	
	@PostMapping("/supplier/add")
	public String postAddSupplier(@ModelAttribute Supplier supplier,
			BindingResult result,
			RedirectAttributes attributes) {
		if(!result.hasErrors()) {
			supplierService.add(supplier);
			attributes.addFlashAttribute("message", "Failed");
			attributes.addFlashAttribute("alertClass", "alert-danger");
			return "redirect:/suppliers";
		}
		attributes.addFlashAttribute("message", "Failed");
		attributes.addFlashAttribute("alertClass", "alert-danger");
		return "supplier_add";
	}
	
	@RequestMapping("/supplier/delete/{id}")
	public String deleteSupplier(@PathVariable String id) {
		supplierService.delete(Long.parseLong(id));
		return "redirect:/suppliers";
	}
	
	
}
