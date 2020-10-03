package za.co.setvin.bootstrap;

import java.math.BigDecimal;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import za.co.setvin.entity.Customer;
import za.co.setvin.entity.Load;
import za.co.setvin.service.CustomerService;
import za.co.setvin.service.LoadService;

@Component
public class Bootstrap {
	
	private CustomerService customerService;
	
	private LoadService loadService;
	
	@Autowired
	public Bootstrap(CustomerService customerService, LoadService loadService) {
		this.customerService = customerService;
		this.loadService = loadService;
//		loadCustomers();
		loadTruckLoads();
	}
	
	private void loadCustomers() {
		Customer customer = new Customer();
		customer.setName("setvin");
		customer.setEmail("cschirwa@gmail.com");
		customer.setDefaultCcy("ZAR");
		customer.setUsername("setvin");
		customer.setPassword("12341234");
		customer.setPhone("1234567890");
		customer.setVatable(true);
		customerService.add(customer);
	}
	
	private void loadTruckLoads() {
		Load load = Load.builder()
				.orderNumber("12345")
				.description("Load to Durban - Manganese 30MT")
				.instructions("Load by 8am, offload by 3pm")
				.loadDate(new Date())
				.deliveryDate(new Date())
				.fromAddress("Johannesburg")
				.fromSuburb("City Deep")
				.fromCountry("South Africa")
				.toAddress("Durban")
				.toSuburb("Umshaka")
				.toCountry("South Africa")
				.quantity(34)
				.rate(new BigDecimal(1000))
				.totalAmount(new BigDecimal(34000))
				.chargeVat(true)
				.currency("ZAR")
				.build();
		loadService.add(load);
	}

}
