package za.co.setvin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class OrderController {

	@GetMapping("/orders")
	public String showOrders() {
		return "loads";
	}
	
	@GetMapping("order_add")
	public String addOrder() {
		return "order_add";
	}
	
	@GetMapping("order_view")
	public String viewOrder() {
		return "order_view";
	}
	
	@GetMapping("order_edit")
	public String editOrder() {
		return "order_edit";
	}
}
