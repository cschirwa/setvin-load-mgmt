package za.co.setvin.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;

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
	
	private String phone;
	
	private Long invoiceNumber;
	
	private Date invoiceDate;
	
}
