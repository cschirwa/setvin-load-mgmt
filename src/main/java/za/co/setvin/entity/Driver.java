package za.co.setvin.entity;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

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
	
//	@Size(min = 13, max = 13)
	@Pattern(regexp = "^(?:[0-9]{13}|)$")
	private String idNumber;
	private String passportNumber;
	private String nationality;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate dob;
	
	private String licenceNumber;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate licenceExpiry;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate medicalsExpiry;
	
	@OneToOne
	private Truck currentTruck;
}
