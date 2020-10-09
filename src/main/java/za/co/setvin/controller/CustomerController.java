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

import za.co.setvin.entity.Country;
import za.co.setvin.entity.Currency;
import za.co.setvin.entity.Customer;
import za.co.setvin.service.CountryService;
import za.co.setvin.service.CurrencyService;
import za.co.setvin.service.CustomerService;

@Controller
public class CustomerController {

	@Autowired
	private CustomerService customerService;
	
	@Autowired
	private CurrencyService currencyService;
	
	@Autowired
	private CountryService countryService;
	
	@GetMapping("/customers")
	public String showCustomers(Model model) {
		List<Customer> customers = customerService.getAll();
		model.addAttribute("customerList", customers);
		return "customers";
	}
	
	@GetMapping("/customer_add")
	public String viewAddCustomer(Model model) {
		List<Currency> currencies = currencyService.getAll();
		List<Country> countries = countryService.getAll();
		model.addAttribute("customer", new Customer());
		model.addAttribute("currencyList", currencies);
		model.addAttribute("countryList", countries);
		return "customer_add";
	}
	
	@GetMapping("/customer_view/{customerId}")
	public String viewCustomer(@PathVariable("customerId") Long id, Model model) {
		Customer customer = customerService.findCustomer(id);
		
		if(customer!=null) {
			model.addAttribute("customer", customer);
		}
		return "customer_view";
	}
	
	@GetMapping("/customer_edit/{customerId}")
	public String editCustomer(@PathVariable("customerId") Long id, Model model) {
		Customer customer = customerService.findCustomer(id);
		if(customer!=null) {
			List<Currency> currencies = currencyService.getAll();
			List<Country> countries = countryService.getAll();
			model.addAttribute("currencyList", currencies);
			model.addAttribute("countryList", countries);
			model.addAttribute("customer", customer);
		}
		return "customer_edit";
	}

	@PostMapping("/customer_add")
	public String postAddCustomer(
			@ModelAttribute Customer customer, 
			BindingResult result,
			RedirectAttributes attributes) {
		if(!result.hasErrors()) {
			customerService.add(customer);
			attributes.addFlashAttribute("message", "Success");
			attributes.addFlashAttribute("alertClass", "alert-success");
			return "redirect:customers";
		}
		attributes.addFlashAttribute("message", "Failed");
		attributes.addFlashAttribute("alertClass", "alert-danger");
		return "customer_add";
	}
	
	@PostMapping("customer_edit/{customerId}")
	public String updateCustomer(
			@PathVariable String customerId,
			@ModelAttribute Customer customer, 
			BindingResult result,
			RedirectAttributes attributes) {
		if(!result.hasErrors()) {
			Customer dbCustomer = customerService.findCustomer(Long.parseLong(customerId));
			if(dbCustomer!=null) {
				customerService.amend(Long.parseLong(customerId), customer);
			}
			attributes.addFlashAttribute("message", "Failed");
			attributes.addFlashAttribute("alertClass", "alert-danger");
			return "redirect:/customers";
		}
		attributes.addFlashAttribute("message", "Failed");
		attributes.addFlashAttribute("alertClass", "alert-danger");
		return "customer_edit";
	}
}
