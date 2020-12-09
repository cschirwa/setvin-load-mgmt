package za.co.setvin.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity(name = "receipt")
public class Receipt extends AbstractEntity implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "customer_id", nullable = false)
	private Customer customer;
	
	private BigDecimal amountReceived;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate dateReceived;
	
	private BigDecimal unallocatedAmount;
	
}
