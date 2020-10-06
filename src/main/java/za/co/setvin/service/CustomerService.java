package za.co.setvin.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.apache.commons.text.WordUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import za.co.setvin.entity.Customer;
import za.co.setvin.exception.CustomerNotFoundException;
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
	
	public Customer findCustomer(Long id) {
		return customerRepository.findById(id).orElseThrow(() -> new CustomerNotFoundException("Customer not found in database"));
	}
	
	@Transactional
	public Customer add(Customer customer) {
		if (customer != null) {
			customer = checkCases(customer);
			return customerRepository.save(customer);
		}
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
	
	@Transactional
	public Customer amend(Long id, Customer customer) {
			customer.setId(id);
			return customerRepository.save(customer);
	}
	
	private Customer checkCases(Customer customer) {
		customer.setName(WordUtils.capitalize(customer.getName()));
		customer.setEmail(customer.getEmail().toLowerCase());
		customer.setContactPerson(WordUtils.capitalizeFully(customer.getContactPerson()));
		return customer;
	}
	

}
