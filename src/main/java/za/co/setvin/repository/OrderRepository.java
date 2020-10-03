package za.co.setvin.repository;

import org.springframework.data.repository.CrudRepository;

import za.co.setvin.entity.Order;

public interface OrderRepository extends CrudRepository<Order, Long>{

}
