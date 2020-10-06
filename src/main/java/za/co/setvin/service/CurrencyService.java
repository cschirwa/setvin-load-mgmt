package za.co.setvin.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import za.co.setvin.entity.Currency;
import za.co.setvin.exception.CurrencyNotFoundException;
import za.co.setvin.repository.CurrencyRepository;

@Service
public class CurrencyService {
	
	@Autowired
	private CurrencyRepository currencyRepository;
	
	private static final Logger log = LoggerFactory.getLogger(CurrencyService.class);

	public Currency add(Currency currency) {
		return currencyRepository.save(currency);
	}
	
	public List<Currency> getAll(){
		List<Currency> currencies = new ArrayList<>();
		currencyRepository.findAll().forEach(currency -> currencies.add(currency));
		return currencies;
	}
	
	public Currency amend(Long id, Currency currency) {
		Currency dbCurrency = currencyRepository.findById(id).isPresent() ? currencyRepository.findById(id).get() : null;
		if(dbCurrency!=null) {
			dbCurrency = currency;
			dbCurrency.setId(id);
			return currencyRepository.save(currency);
		}
		return currencyRepository.save(currency);
	}
	
	public void delete(Long currencyId) {
		if(currencyRepository.findById(currencyId).isPresent()) {
			currencyRepository.deleteById(currencyId);
			log.info("Currency " + currencyId + " deleted");
		}
	}
	
	public Currency findById(Long id) {
		if(!currencyRepository.findById(id).isPresent())
			throw new CurrencyNotFoundException("Currency Not Found On Database");
		return currencyRepository.findById(id).get();
	}
	
	public Currency findByIsoCode(String isoCode) {
		if(!currencyRepository.findByIsoCode(isoCode).isPresent())
			throw new CurrencyNotFoundException("Currency " + isoCode + " Not Found On Database");
		return currencyRepository.findByIsoCode(isoCode).get();
	}
	
	public Currency findByName(String name) {
		if(!currencyRepository.findByName(name).isPresent())
			throw new CurrencyNotFoundException("Currency " + name + " Not Found On Database");
		return currencyRepository.findByName(name).get();
	}
	
	
}
