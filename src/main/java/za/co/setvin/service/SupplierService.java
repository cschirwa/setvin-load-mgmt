package za.co.setvin.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import za.co.setvin.entity.Supplier;
import za.co.setvin.exception.SupplierNotFoundException;
import za.co.setvin.repository.SupplierRepository;

@Service
public class SupplierService {
	
	@Autowired
	private SupplierRepository supplierRepository;
	
	public Supplier add(Supplier supplier) {
		if(supplier!=null)
			return supplierRepository.save(supplier);
		throw new IllegalArgumentException("Invalid Supplier supplied");
	}
	
	public Supplier findById(Long id) {
		return supplierRepository.findById(id).orElseThrow(() -> new SupplierNotFoundException());
	}
	
	public void delete(Long id) {
		supplierRepository.deleteById(id);
	}
	
	public Supplier amend(Long id, Supplier supplier) {
		if(supplier!=null) {
			if(supplierRepository.findById(id).isPresent()) 
				supplier.setId(id);
			return supplierRepository.save(supplier);
		}
		throw new IllegalArgumentException("Null supplier supplied");
	}
	
	public List<Supplier> getAll(){
		List<Supplier> suppliers = new ArrayList<>();
		supplierRepository.findAll().forEach(s -> suppliers.add(s));
		return suppliers;
	}

}
