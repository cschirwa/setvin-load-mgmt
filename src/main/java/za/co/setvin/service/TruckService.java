package za.co.setvin.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import za.co.setvin.entity.Driver;
import za.co.setvin.entity.Truck;
import za.co.setvin.repository.TruckRepository;

@Service
public class TruckService {

	@Autowired
	private TruckRepository truckRepository;
	
	public Truck findById(Long id) {
		return truckRepository.findById(id).isPresent() ? truckRepository.findById(id).get() : null;
	}
	
	public Truck findByDriver(Driver driver) {
		return truckRepository.findByDriver(driver).isPresent() ? truckRepository.findByDriver(driver).get() : null;
	}
	
	public Truck add(Truck truck) {
		if(truck!=null)
			return truckRepository.save(truck);
		throw new IllegalArgumentException("Invalid truck - cannot be saved to database");
	}
	
	public Truck findByRegistration(String registration)  {
		if(!registration.isEmpty())
			return truckRepository.findByRegistration(registration).isPresent() ? 
					truckRepository.findByRegistration(registration).get() : null;
		return null;
	}
}
