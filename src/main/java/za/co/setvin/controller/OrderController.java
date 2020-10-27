package za.co.setvin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import za.co.setvin.entity.Order;

@Controller
public class OrderController {

	@GetMapping("/orders")
	public String showOrders() {
		return "orders";
	}
	
	@GetMapping("/order/add")
	public String addOrder(Model model) {
		model.addAttribute("order", new Order());
		return "order_add";
	}
	
	@GetMapping("/order/view")
	public String viewOrder() {
		return "order_view";
	}
	
	@GetMapping("/order/edit")
	public String editOrder() {
		return "order_edit";
	}
}
