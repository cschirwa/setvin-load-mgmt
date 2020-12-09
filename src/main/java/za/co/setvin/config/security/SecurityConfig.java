package za.co.setvin.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	public SecurityConfig(PasswordEncoder passwordEncoder) {
		this.passwordEncoder = passwordEncoder;
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.csrf().disable()
//			.sessionManagement()
//				.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//			.and()
			.authorizeRequests()
			.antMatchers("/static/**","/login","/css/**","/js/**","/vendor/**").permitAll()
//			.antMatchers("/index").hasRole("ADMIN")
			.anyRequest()
			.authenticated()
			.and()
			.formLogin()
			.loginPage("/login").permitAll()
			.defaultSuccessUrl("/index", true)
//			.failureForwardUrl("/loginFail");
			.and()
			.headers().frameOptions().sameOrigin();

	}


	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//		auth.authenticationProvider(daoAuthenticationProvider());
		auth.inMemoryAuthentication()
			.withUser("calvin")
			.password(passwordEncoder.encode("123123"))
			.accountExpired(false)
			.accountLocked(false)
			.credentialsExpired(false)
			.disabled(false)
			.authorities("ROLE_ADMIN");
	}

//	@Bean
//	public DaoAuthenticationProvider daoAuthenticationProvider() {
//		DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
//		provider.setUserDetailsService(userService);
//		provider.setPasswordEncoder(passwordEncoder);
//		return provider;
//	}
	

//	@Bean
//	@Override
//	public UserDetailsService userDetailsService() {
//		UserDetails calvinUser = User.builder()
//				.username("calvin")
//				.password(passwordEncoder.encode("123123"))
//				.roles("ADMIN")		//ROLE_ADMIN
//				.build();
//
//		return new InMemoryUserDetailsManager(calvinUser);
//
//	}
}
