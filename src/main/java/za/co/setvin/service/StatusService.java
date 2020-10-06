package za.co.setvin.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import za.co.setvin.entity.Status;
import za.co.setvin.repository.StatusRepository;

@Service
public class StatusService {
	
	@Autowired
	private StatusRepository statusRepository;
	
	public Status save(Status status) {
		if(status!=null)
			return statusRepository.save(status);
		throw new IllegalArgumentException("Invalid Status supplied");
	}
	
	public Status findByName(String name) {
		 if(!statusRepository.findByName(name).isPresent())
			 throw new IllegalArgumentException("Status " + name + " Not Found");
		 return statusRepository.findByName(name).get();
	}
	
	public List<Status> getAll(){
		List<Status> statusList = new ArrayList<>();
		statusRepository.findAll().forEach(status -> statusList.add(status));
		return statusList;
	}

}
