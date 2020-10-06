package za.co.setvin.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import za.co.setvin.entity.Currency;

public interface CurrencyRepository extends CrudRepository<Currency, Long> {
	
	Optional<Currency> findByIsoCode(String isoCode);
	
	Optional<Currency> findByName(String currencyName);

}
