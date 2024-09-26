package za.co.setvin.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import za.co.setvin.entity.Country;
import za.co.setvin.repository.CountryRepository;

@Service
public class CountryService {
	
	@Autowired
	private CountryRepository countryRepository;
	
	private static final Logger log = LoggerFactory.getLogger(CountryService.class);
	 
	public List<Country> getAll(){
		List<Country> countries = new ArrayList<>();
		countryRepository.findAll().forEach(country -> countries.add(country));
		return countries;
	}
	
	public Country save(Country country) {
		if(country!=null) {
			log.info(country + " saved");
			return countryRepository.save(country);
		}
		throw new IllegalArgumentException("Country " + country.toString() + " not saved to database");
	}
	
	public void delete(String countryName) {
		if(!countryRepository.findByName(countryName).isPresent())
			log.info(countryName + " not found on database");
		Country country = countryRepository.findByName(countryName).get();
		countryRepository.deleteById(country.getId());
		log.info(countryName + " deleted from database");
	}
	
	public Country findByName(String countryName) {
		if(!countryRepository.findByName(countryName).isPresent())
			log.info(countryName + " not found on database");
		return countryRepository.findByName(countryName).get();
	}

}
