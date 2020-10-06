package za.co.setvin.repository;

import org.springframework.data.repository.CrudRepository;

import za.co.setvin.entity.Trailer;

public interface TrailerRepository extends CrudRepository<Trailer, Long>{

//	List<Trailer> findAllIfAvailable();
	
}
