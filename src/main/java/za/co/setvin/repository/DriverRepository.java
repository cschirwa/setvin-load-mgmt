package za.co.setvin.repository;


import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import za.co.setvin.entity.Driver;

public interface DriverRepository extends CrudRepository<Driver, Long> {
	
	Optional<Driver> findByFirstname(String name);

}
