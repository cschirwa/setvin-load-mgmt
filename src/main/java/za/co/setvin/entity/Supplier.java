package za.co.setvin.entity;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.validation.constraints.Pattern;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity(name = "supplier")
public class Supplier extends AbstractEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String name;
	
	private String contact;
	
	private String email;
	
	private String phone;
	
	private String phone2;
	
	private String enterpriseNumber;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate invoiceDate;
	
	private String address;
	
	private String country;
	
	@Column(name = "vat_number", nullable = true, unique = false)
	@Pattern(regexp = "^(?:[4-5][0-9]{9}|)$")
	private String vatNumber;
	
}
