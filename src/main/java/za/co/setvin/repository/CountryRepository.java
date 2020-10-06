package za.co.setvin.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import za.co.setvin.entity.Country;

public interface CountryRepository extends CrudRepository<Country, Long>{

	Optional<Country> findByName(String countryName);
}
