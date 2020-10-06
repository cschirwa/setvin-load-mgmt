package za.co.setvin.entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.springframework.lang.Nullable;

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
	@Size(min = 2, max = 35, message = "{invalid.name}")
	private String name;
	
	@Column(name = "vat_number", nullable = true, unique = false)
	@Pattern(regexp = "^(?:[4-5][0-9]{9}|)$")
//	@Size(min = 10, max = 10, message = "{invalid.vatnumber}")
	private String VatNumber;
	
	@Column(name = "enterprise_number", nullable = true, unique = false)
	@Size( message = "{invalid.enterprise.number}")
	@Pattern(regexp = "^(?:[1-2][0-9]{3}[//]?[0-9]{6}[//]?[0-9]{2}|)$")
	private String enterpriseNumber;
	
	@Column(name = "contact_person", nullable = false, unique = false)
	@Size(min = 2, max = 35, message = "{invalid.name}")
	private String contactPerson;
	
	@Email
	@Column(name = "email", nullable = false, unique = false)
	private String email;
	
	@Column(name = "phone", nullable = false)
//	@Size(min = 9, max = 12, message = "{invalid.phonenumber}")
	@Pattern(regexp = "^(?:[+0]{1}[0-9]{7,11}|)$")
	private String phone;
	
	@Nullable
	@Column(name = "phone2", nullable = true, unique = false)
//	@Size(max = 12, message = "{invalid.phonenumber}")
	@Pattern(regexp = "^(?:[+0]{1}[0-9]{7,11}|)$")
	private String phone2;
	
	@Column(name = "address", nullable = true, unique = false)
	@Size(min = 3, max = 55, message = "{invalid.address}")
	private String address;
	
	private String country;
	
	private String defaultCcy;
	
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "customer", cascade = CascadeType.DETACH)
	private Set<Receipt> receipts = new HashSet<>();
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "customer", cascade = CascadeType.ALL)
	private Set<Load> loads = new HashSet<>();

}
