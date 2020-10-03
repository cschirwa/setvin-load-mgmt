package za.co.setvin.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;

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
	private String nationality;
	private Date dob;
	private String licenseNumber;
	private Date licenseExpiry;
	private Date medicalsExpiry;
}
