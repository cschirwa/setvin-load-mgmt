package za.co.setvin.entity;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Entity;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity(name = "trailer")
@EqualsAndHashCode(callSuper = true)
public class Trailer extends AbstractEntity implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String registration;
	private String make;
	private String model;
	private boolean isAvailable;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate licenseDiscExpiry;

}
