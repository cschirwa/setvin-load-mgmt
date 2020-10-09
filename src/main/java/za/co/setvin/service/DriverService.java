package za.co.setvin.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import za.co.setvin.entity.Driver;
import za.co.setvin.exception.DriverNotFoundException;
import za.co.setvin.repository.DriverRepository;

@Service
public class DriverService {
	
	@Autowired
	private DriverRepository driverRepository;
	
	public Driver findById(Long id) {
		return driverRepository.findById(id).orElseThrow(() -> new DriverNotFoundException("Driver "+id+" not found on database"));
	}
	
	public Driver add(Driver driver) {
		if(driver!=null)
			return driverRepository.save(driver);
		throw new IllegalArgumentException("Invalid driver supplied, cannot save to database.");
	}
	
	public List<Driver> getAll(){
		List<Driver> drivers = new ArrayList<>();
		driverRepository.findAll().forEach(driver -> drivers.add(driver));
		return drivers;
	}
	
	public List<Driver> findAvailableDrivers(){
		List<Driver> drivers = new ArrayList<>();
		driverRepository.findAll().forEach(driver -> {
			if(driver.getCurrentTruck()==null)
				drivers.add(driver);
		});
		return drivers;
	}
	
	public Driver findByFirstName(String name) {
		if(name.isEmpty())
			throw new IllegalArgumentException("Illegal driver name specified");
		return driverRepository.findByFirstname(name).orElseThrow(() -> new DriverNotFoundException());
	}

	public Driver amend(long driverId, Driver driver) {
		if(driverRepository.existsById(driverId)) {
			driver.setId(driverId);
			return driverRepository.save(driver);
		}
		return driverRepository.save(driver);
		
	}

}
