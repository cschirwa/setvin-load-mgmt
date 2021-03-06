package za.co.setvin.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Entity(name = "purchase_order")
public class Order extends AbstractEntity implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String purchaseType;
	
	@OneToMany(mappedBy = "order", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JsonIgnoreProperties("order")
	private List<LineItem> lineItems;
	
	private long vatRate;
	
	private BigDecimal vat;
	
	private String status;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate orderDate;
	
	private BigDecimal orderTotalAmount;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate paymentDate;
	
	private BigDecimal amountPaid;
	
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "supplier_id", nullable = false)
	private Supplier supplier;
	
	public BigDecimal getSubTotal() {
        BigDecimal subTotal = new BigDecimal(0);
        getLineItems().forEach(lineItem -> {
            subTotal.add(lineItem.getLineItemTotal());
        });
        return subTotal;
    }

	public BigDecimal getVat() {
		return getSubTotal().multiply(BigDecimal.valueOf(getVatRate()/100));
	}
	public BigDecimal getTotal() {
		return getSubTotal().add(getVat());
	}
}
