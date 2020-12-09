package za.co.setvin.entity;

import java.io.Serializable;

import javax.persistence.Entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity(name ="status")
public class Status extends AbstractEntity implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String name;
	private String narration;
	
	public Status(String name, String narration) {
		super();
		this.name = name;
		this.narration = narration;
	}
}
