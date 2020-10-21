package za.co.setvin.entity;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Entity;

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
	
	private String invoiceNumber;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate invoiceDate;
	
}
