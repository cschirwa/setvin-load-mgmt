package za.co.setvin.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import za.co.setvin.entity.Order;
import za.co.setvin.exception.InvalidOrderException;
import za.co.setvin.repository.OrderRepository;

@Service
public class OrderService {
	
	@Autowired
	private OrderRepository orderRepository;
	
	public static final Logger log = LoggerFactory.getLogger(OrderService.class);
	
	public List<Order> getAll(){
		List<Order> orders = new ArrayList<>();
		orderRepository.findAll().forEach(order -> orders.add(order));
		return orders;
	}
	
	public Order add(final Order order) {
		if(order!=null) {
			Order dbOrder = orderRepository.save(order);
			log.info("Order %s saved to database", dbOrder.getId());
			return dbOrder;
		}
		log.error("Invalid order - not saved to database");
		throw new InvalidOrderException();
	}
	
	public void delete(Long id) {
		if(orderRepository.existsById(id)) {
			orderRepository.deleteById(id);
			log.info("Order %s deleted from db");
		}
		log.error("Order %s not found on the database", id);
	}
	
	public Order amend(Long id, Order order) {
		Order dbOrder = orderRepository.existsById(id) ? orderRepository.findById(id).get() : null;
		if(dbOrder!=null) {
			dbOrder = order;
			dbOrder.setId(id);
			orderRepository.save(dbOrder);
			log.info("Order %s saved to database", id);
			return dbOrder;
		}
		log.info("New order saved to database");
		return orderRepository.save(order);
	}

}
