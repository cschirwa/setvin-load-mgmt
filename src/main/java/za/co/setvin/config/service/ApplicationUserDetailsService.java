package za.co.setvin.config.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import za.co.setvin.config.security.ApplicationUserDetails;
import za.co.setvin.entity.User;
import za.co.setvin.repository.UserRepository;
@Service
public class ApplicationUserDetailsService implements UserDetailsService{

	@Autowired
	private UserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) {
		Optional<User> user = userRepository.findByUsername(username);
		if(user.isPresent()) {
			return new ApplicationUserDetails(user.get());
		}
		throw new UsernameNotFoundException(String.format("%s not found on database", username));
	}

}
