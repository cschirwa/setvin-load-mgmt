package za.co.setvin.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.OneToOne;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
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
	

}
