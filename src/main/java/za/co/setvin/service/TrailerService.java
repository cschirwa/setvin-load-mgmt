package za.co.setvin.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import za.co.setvin.entity.Trailer;
import za.co.setvin.exception.TrailerNotFoundException;
import za.co.setvin.repository.TrailerRepository;

@Service
public class TrailerService {

	@Autowired
	private TrailerRepository trailerRepository;
	
	public Trailer findById(Long id) {
		return trailerRepository.findById(id).orElseThrow(() -> new TrailerNotFoundException("Trailer: " 
				+ id + " not found in database"));
	}
	
	public List<Trailer> findAllIfAvailable(){
		List<Trailer> trailers = new ArrayList<>();
		trailerRepository.findAll().forEach(trailer -> {
			if(trailer.isAvailable())
				trailers.add(trailer);
		});
		return trailers;
	}
}
