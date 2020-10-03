package za.co.setvin.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import za.co.setvin.entity.Customer;
import za.co.setvin.exception.InvalidCustomerException;
import za.co.setvin.repository.CustomerRepository;

@Service
public class CustomerService {

	private static final Logger log = LoggerFactory.getLogger(CustomerService.class);

	private final CustomerRepository customerRepository;

	@Autowired
	public CustomerService(CustomerRepository customerRepository) {
		this.customerRepository = customerRepository;
	}

	public List<Customer> getAll(){
		List<Customer> customers = new ArrayList<>();
		customerRepository.findAll().forEach(customer -> customers.add(customer));
		return customers;
		
	}
	
	public Customer add(final Customer customer) {
		if (customer != null)
			return customerRepository.save(customer);
		log.error("Failed to save customer to db");
		throw new InvalidCustomerException();
	}

	public void delete(Long customerId) {
		if (customerId != null) {
			if (customerRepository.existsById(customerId)) {
				customerRepository.deleteById(customerId);
				log.info("Customer %s deleted", customerId);
				return;
			}
		}
		log.error("Customer %s not found", customerId);
	}
	
	public Customer amend(Long id, Customer customer) {
		Customer dbCustomer = customerRepository.findById(id).isPresent() ? customerRepository.findById(id).get() : null;
		if(dbCustomer!=null) {
			dbCustomer = customer;
			dbCustomer.setId(id);
			log.info("Customer %s saved", id);
			return customerRepository.save(dbCustomer);
		}
		return customerRepository.save(customer);
	}
	

}
