package za.co.setvin.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity(name = "truck_loads")
@Builder
@Data
@EqualsAndHashCode(callSuper = true)
public class Load extends AbstractEntity implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String orderNumber;
	private String description;
	private String instructions;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate loadDate;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate deliveryDate;
	private String fromAddress;
	private String fromSuburb;
	private String fromCountry;
	private String toAddress;
	private String toSuburb;
	private String toCountry;
	private int quantity;
	private BigDecimal rate;
	private BigDecimal totalAmount;
	private boolean chargeVat;
	private String currency;
	private String clearingAgent;
	private String agentContact;
	private String agentPhone;
	private String exitBorder;
	private String entryBorder;
	private String freightStatement;
	
//	@OneToMany(mappedBy = "load", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
//	@JsonIgnoreProperties("load")
//	private Map<String, String> containerMap;
	
	private String customer;
	
	private String truck;
	
	private String driver;
	
	@OneToMany
	private List<Trailer> trailers;
	
//	@ManyToOne(fetch = FetchType.EAGER)
	private String status;
	
	public Load() {
		super();
		this.loadDate = LocalDate.now();
		this.chargeVat = true;
		this.trailers = new ArrayList<>();
	}

	public Load(String orderNumber, 
			String description, 
			String instructions, 
			LocalDate loadDate, 
			LocalDate deliveryDate,
			String fromAddress, 
			String fromSuburb, 
			String fromCountry, 
			String toAddress, 
			String toSuburb,
			String toCountry, 
			int quantity, 
			BigDecimal rate, 
			BigDecimal totalAmount, 
			boolean chargeVat, 
			String currency,
			String clearingAgent,
			String agentContact,
			String agentPhone,
			String exitBorder, 
			String entryBorder,
			String freightStatement,
			String customer, 
			String truck,
			String driver,
			List<Trailer> trailers,
			String status
			) {
		super();
		this.orderNumber = orderNumber;
		this.description = description;
		this.instructions = instructions;
		this.loadDate = loadDate;
		this.deliveryDate = deliveryDate;
		this.fromAddress = fromAddress;
		this.fromSuburb = fromSuburb;
		this.fromCountry = fromCountry;
		this.toAddress = toAddress;
		this.toSuburb = toSuburb;
		this.toCountry = toCountry;
		this.quantity = quantity;
		this.rate = rate;
		this.totalAmount = totalAmount;
		this.chargeVat = chargeVat;
		this.currency = currency;
		this.clearingAgent = clearingAgent;
		this.agentContact = agentContact;
		this.agentPhone = agentPhone;
		this.exitBorder = exitBorder;
		this.entryBorder = entryBorder;
		this.freightStatement = freightStatement;
		this.customer = customer;
		this.truck = truck;
		this.trailers = trailers;
		this.driver = driver;
		this.status = status;
	}
	
	
	
}
