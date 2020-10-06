package za.co.setvin.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import za.co.setvin.entity.Customer;

@Repository
public interface CustomerRepository extends CrudRepository<Customer, Long>{

}
