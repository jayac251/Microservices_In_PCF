package io.jc.sales.repo;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class SalesOrder {
	
	@Id
	@GeneratedValue
	@Column(name="order_id")
	Long orderId;	
	String orderDate;
	Long custId;
	String orderDescription;
	String totalPrice;
	// @OneToMany(fetch = FetchType.LAZY, mappedBy = "salesOrder")
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "order_id",referencedColumnName="order_id")
	 private List<OrderLineItems> orderLineItems;
	
	public SalesOrder() {
		super();
		// TODO Auto-generated constructor stub
	}
	/**
	 * @param orderDate
	 * @param custId
	 * @param orderDescription
	 * @param items
	 * @param totalPrice
	 */
	public SalesOrder(String orderDate, Long custId, String orderDescription, String totalPrice, List<OrderLineItems> orderLineItems) {
		super();
		this.orderDate = orderDate;
		this.custId = custId;
		this.orderDescription = orderDescription;
		this.totalPrice = totalPrice;
		this.orderLineItems = orderLineItems;
	}
	/**
	 * @return the id
	 */
	public Long getOrderId() {
		return orderId;
	}
	/**
	 * @param id the id to set
	 */
	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}
	/**
	 * @return the orderDate
	 */
	public String getOrderDate() {
		return orderDate;
	}
	/**
	 * @param orderDate the orderDate to set
	 */
	public void setOrderDate(String orderDate) {
		this.orderDate = orderDate;
	}
	/**
	 * @return the custId
	 */
	public Long getCustId() {
		return custId;
	}
	/**
	 * @param custId the custId to set
	 */
	public void setCustId(Long custId) {
		this.custId = custId;
	}
	/**
	 * @return the orderDescription
	 */
	public String getOrderDescription() {
		return orderDescription;
	}
	/**
	 * @param orderDescription the orderDescription to set
	 */
	public void setOrderDescription(String orderDescription) {
		this.orderDescription = orderDescription;
	}
	
	/**
	 * @return the totalPrice
	 */
	public String getTotalPrice() {
		return totalPrice;
	}
	/**
	 * @param totalPrice the totalPrice to set
	 */
	public void setTotalPrice(String totalPrice) {
		this.totalPrice = totalPrice;
	}
	/**
	 * @return the orderLineItems
	 */
	public List<OrderLineItems> getOrderLineItems() {
		return orderLineItems;
	}
	/**
	 * @param orderLineItems the orderLineItems to set
	 */
	public void setOrderLineItems(List<OrderLineItems> orderLineItems) {
		this.orderLineItems = orderLineItems;
	}	
	
	
}
