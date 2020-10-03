package za.co.setvin.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
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
	
	private Date loadDate;
	
	private Date deliveryDate;
	
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
	
	private String exitBorder;
	
	private String entryBorder;
	
	private String freightStatement;
	
	
//	@OneToMany(mappedBy = "load", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
//	@JsonIgnoreProperties("load")
//	private Map<String, String> containerMap;
	
	@ManyToOne
	private Customer customer;
	
	private Truck truck;
	
	public Load() {
		super();
		this.loadDate = new Date();
//		this.containerMap = new TreeMap<>();
		this.chargeVat = true;
	}

	public Load(String orderNumber, String description, String instructions, Date loadDate, Date deliveryDate,
			String fromAddress, String fromSuburb, String fromCountry, String toAddress, String toSuburb,
			String toCountry, int quantity, BigDecimal rate, BigDecimal totalAmount, boolean chargeVat, String currency,
			String clearingAgent, String agentContact, String exitBorder, String entryBorder, String freightStatement,
//			Map<String, String> containerMap, 
			Customer customer, Truck truck) {
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
		this.exitBorder = exitBorder;
		this.entryBorder = entryBorder;
		this.freightStatement = freightStatement;
//		this.containerMap = containerMap;
		this.customer = customer;
		this.truck = truck;
	}
	
	
	
}
