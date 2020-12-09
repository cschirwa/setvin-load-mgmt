package za.co.setvin.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import za.co.setvin.entity.User;

public interface UserRepository extends CrudRepository<User, Long> {

	User findByUsername(String username);

	User findByFirstname(String firstname);
}