package za.co.setvin.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import za.co.setvin.entity.User;
import za.co.setvin.exception.UserNotFoundException;
import za.co.setvin.repository.UserRepository;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	public User findById(Long id) {
		return userRepository.findById(id).orElseThrow(() -> new UserNotFoundException());
	}
	
	public User findByUsername(String username) {
		return userRepository.findByUsername(username).orElseThrow(() -> new UserNotFoundException());
	}
	
	public User save(User user) {
		return userRepository.save(user);
	}
	
	public void delete(Long userId) {
		userRepository.deleteById(userId);
	}
}
