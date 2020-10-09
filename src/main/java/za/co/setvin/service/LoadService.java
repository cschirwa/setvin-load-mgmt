package za.co.setvin.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import za.co.setvin.entity.Load;
import za.co.setvin.exception.InvalidLoadException;
import za.co.setvin.repository.LoadRepository;

@Service
public class LoadService {
	
	private static Logger log = LoggerFactory.getLogger(LoadService.class);
	
	@Autowired
	private LoadRepository loadRepository;
	
	private static final double VAT_RATE = 0.15D;
	
	
	public Load get(Long id) {
		if(loadRepository.findById(id).isPresent())
			return loadRepository.findById(id).get();
		return null;
	}
	
	public List<Load> getAll(){
		List<Load> loads = new ArrayList<>();
		loadRepository.findAll().forEach(load -> loads.add(load));
		return loads;
	}
	
	public Load add(final Load load) {
		if(load!=null) {
			log.info("Load saved to db");
			return loadRepository.save(load);
		}
		log.error("Invalid load, cannot be saved to database");
		throw new InvalidLoadException();
	}
	
	public void delete(Long id) {
		if(loadRepository.existsById(id)) {
			loadRepository.deleteById(id);
			log.info("Load %s deleted from database", id);
			return;
		}
		log.error("Load %s not found on database", id);
	}
	
	public Load amend(Long id, final Load load) {
		Load dbLoad = loadRepository.findById(id).isPresent() ? loadRepository.findById(id).get() : null;
		if(dbLoad!=null) {
			dbLoad = load;
			dbLoad.setId(id);
			return loadRepository.save(dbLoad);
		}
		return loadRepository.save(load);
	}
	
	public BigDecimal getSubTotal(final Load load) {
		BigDecimal subTotal = new BigDecimal(0);
		if(load!=null) 
			subTotal.add(new BigDecimal(load.getQuantity()).multiply((load.getRate())));
		return subTotal;
		
	}
	
	public BigDecimal getVat(final Load load) {
		BigDecimal vat = new BigDecimal(0);
		if(load!=null && load.getCustomer()!=null && !load.getCustomer().getVatNumber().isEmpty()) {
			vat = getSubTotal(load).multiply(new BigDecimal(VAT_RATE));
		}
		return vat;
	}
	
	public BigDecimal getTotal(final Load load) {
		return getSubTotal(load).add(getVat(load));
	}


}
