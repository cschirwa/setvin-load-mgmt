package za.co.setvin.entity;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Entity;

import org.springframework.format.annotation.DateTimeFormat;

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
	private String engineNumber;
	
	private String driver;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate licenseDiscExpiry;
	
	public Truck(String registration, String make, String model, String vin) {
		this.registration = registration;
		this.make = make;
		this.model = model;
		this.vin = vin;
	}
	
	public Truck(String registration, String make, String model, String vin, String engineNumber) {
		this.registration = registration;
		this.make = make;
		this.model = model;
		this.vin = vin;
		this.engineNumber = engineNumber;
	}
	

}
