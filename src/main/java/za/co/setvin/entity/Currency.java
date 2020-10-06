package za.co.setvin.entity;

import java.io.Serializable;

import javax.persistence.Entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "currency")
public class Currency extends AbstractEntity implements Serializable{/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String isoCode;
	private String name;
	

}
