package za.co.setvin.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import za.co.setvin.entity.Driver;
import za.co.setvin.entity.Truck;

public interface TruckRepository extends  CrudRepository<Truck, Long>{

	Optional<Truck> findByRegistration(String registration);
	
	Optional<Truck> findByVin(String vin);
	
	Optional<Truck> findByDriver(Driver driver);
	
	List<Truck> findByMake(String make);
	
	List<Truck> findByModel(String model);
	
	List<Truck> findByLicenseDiscExpiry(LocalDate licenseDiscExpiry);
	
}
