package za.co.setvin.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import za.co.setvin.entity.User;

public interface UserRepository extends CrudRepository<User, Long> {

	Optional<User> findByUsername(String username);

	Optional<User> findByFirstname(String firstname);
}