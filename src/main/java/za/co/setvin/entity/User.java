package za.co.setvin.entity;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.validation.constraints.Size;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import za.co.setvin.config.Role;

@EqualsAndHashCode(callSuper = true)
@Getter
@Setter
@NoArgsConstructor
@Entity(name = "_user")
public class User extends AbstractEntity implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Column(name = "firstname")
	@Size(min = 6, max = 15, message = "Firstname must be between 6 and 15 characters")
	private String firstname;
	
	@Column(name = "lastname")
	@Size(min = 6, max = 15, message = "Lastname must be between 6 and 15 characters")
	private String lastname;
	
	@Column(name = "username", nullable = false, unique = true)
	@Size(min = 6, max = 15, message = "Username must be between 6 and 15 characters")
	private String username;
	
	@Column(name = "password", nullable = false)
	private String password;
	
	@Column(name = "email", unique = true)
	@Size(min = 6, max = 25, message = "Email must be between 6 and 25 characters")
	private String email;
	
	@Column(name = "phone")
	@Size(min = 6, max = 15, message = "Phone must be between 6 and 15 characters")
	private String phone;
	
	@Column(name = "phone2", nullable = true, unique = false)
	@Size(min = 6, max = 15, message = "Phone2 must be between 6 and 15 characters")
	private String phone2;
	
	@Column(name = "location", nullable = true, unique = false)
	@Size(min = 6, max = 35, message = "Location must be between 6 and 35 characters")
	private String location;

	private boolean active;
	private int loginAttempts;
//	@ManyToMany(fetch = FetchType.EAGER)
//	private List<Role> roles;
	
	public User(String username, String password) {
		this.username = username;
		this.password = password;
		this.active = true;
	}

}
