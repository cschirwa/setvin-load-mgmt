package za.co.setvin.config.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import za.co.setvin.config.security.ApplicationUserDetails;
import za.co.setvin.repository.UserRepository;
@Service
@RequiredArgsConstructor
public class ApplicationUserDetailsService implements UserDetailsService{

	private final UserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) {
		if(!userRepository.findByUsername(username).isPresent()){
			throw new UsernameNotFoundException(username + " not found on database");
		}
		return new ApplicationUserDetails(userRepository.findByUsername(username).get());
	}
}
