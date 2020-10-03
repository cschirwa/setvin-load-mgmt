package za.co.setvin.entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.validation.constraints.Size;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity(name = "customer")
public class Customer extends AbstractEntity implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Column(name = "name", nullable = false, unique = false)
	@Size(min = 6, max = 35, message = "Lastname must be between 6 and 35 characters")
	private String name;
	
	@Column(name = "username", nullable = true, unique = true)
	@Size(min = 6, max = 35, message = "Username must be between 6 and 35 characters")
	private String username;
	
	@Column(name = "password", nullable = true)
	@Size(min = 6, max = 15, message = "Password must be between 6 and 15 characters")
	private String password;
	
	@Column(name = "email", nullable = false, unique = true)
	@Size(min = 6, max = 55, message = "Email must be between 6 and 15 characters")
	private String email;
	
	@Column(name = "phone", nullable = false)
	@Size(min = 6, max = 15, message = "Phone must be between 6 and 15 characters")
	private String phone;
	
	@Column(name = "phone2", nullable = true, unique = false)
	@Size(min = 6, max = 15, message = "Phone2 must be between 6 and 15 characters")
	private String phone2;
	
	private boolean isVatable;
	
	private String defaultCcy;
	
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "customer", cascade = CascadeType.DETACH)
	private Set<Receipt> receipts = new HashSet<>();
	

}
