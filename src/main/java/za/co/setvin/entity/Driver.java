package za.co.setvin.entity;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity(name = "driver")
public class Driver extends AbstractEntity implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String firstname;
	private String lastname;
	private String nickname;
	private String idNumber;
	private String passportNumber;
	@ManyToOne
	private Country nationality;
	private LocalDate dob;
	private String licenseNumber;
	private LocalDate licenseExpiry;
	private LocalDate medicalsExpiry;
	@OneToOne
	private Truck currentTruck;
}
