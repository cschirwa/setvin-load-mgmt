package za.co.setvin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SupplierController {

	@GetMapping("/suppliers")
	public String showSuppliers() {
		return "suppliers";
	}
	
	@GetMapping("supplier_add")
	public String addSupplier() {
		return "supplier_add";
	}
	
	@GetMapping("supplier_view")
	public String viewSupplier() {
		return "supplier_view";
	}
}
