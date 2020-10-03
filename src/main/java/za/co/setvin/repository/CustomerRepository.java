package za.co.setvin.repository;

import org.springframework.data.repository.CrudRepository;

import za.co.setvin.entity.Customer;

public interface CustomerRepository extends CrudRepository<Customer, Long>{

}
