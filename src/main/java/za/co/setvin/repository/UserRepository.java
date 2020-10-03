package za.co.setvin.repository;

import org.springframework.data.repository.CrudRepository;

import za.co.setvin.entity.User;

public interface UserRepository extends CrudRepository<User, Long>{

}
