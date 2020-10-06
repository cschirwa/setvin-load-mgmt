package za.co.setvin.entity;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.OneToOne;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity(name = "truck")
@EqualsAndHashCode(callSuper = true)
public class Truck extends AbstractEntity implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String registration;
	private String make;
	private String model;
	private String vin;
	
	@OneToOne
	private Driver driver;
	private LocalDate licenseDiscExpiry;
	
	public Truck(String registration, String make, String model, String vin) {
		this.registration = registration;
		this.make = make;
		this.model = model;
		this.vin = vin;
	}

}
