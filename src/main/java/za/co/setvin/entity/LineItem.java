package za.co.setvin.entity;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Entity
@Table(name = "lineItem")
@Getter
@Setter
public class LineItem extends AbstractEntity implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String product;
	private BigDecimal unitPrice;
	private long quantity;
	private BigDecimal lineTotal;
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "order_id", nullable = false)
	private Order order;
	
	public BigDecimal getLineItemTotal() {
		return this.unitPrice.multiply(BigDecimal.valueOf(quantity));
	}

}
