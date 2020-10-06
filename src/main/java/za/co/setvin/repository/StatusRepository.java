package za.co.setvin.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import za.co.setvin.entity.Status;


public interface StatusRepository extends CrudRepository<Status, Long> {

	Optional<Status> findByName(String name);
}
