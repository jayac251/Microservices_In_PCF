/**
 * 
 */
package io.jc.sales.repo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 * @author 197651
 *id, item_name, item_quantity, order_id
 */
@Entity
public class OrderLineItems {
	
	@Id
	@GeneratedValue
	Long lineItemId;	
	String itemName;
	String itemQuantity;
	
	@Column(name="order_id")
	Long orderId;
	
	//@ManyToOne(fetch = FetchType.LAZY)
  //  @JoinColumn(name = "orderId",nullable=false)
  //  private SalesOrder salesOrder;

	
	/**
	 * @param itemName
	 * @param itemQuantity
	 * @param salesOrder
	 */
	public OrderLineItems(String itemName, String itemQuantity) {
		super();
		this.itemName = itemName;
		this.itemQuantity = itemQuantity;
		//this.salesOrder = salesOrder;
	}
	/**
	 * 
	 */
	public OrderLineItems() {
		super();
		// TODO Auto-generated constructor stub
	}
	/**
	 * @return the id
	 */
	public Long getLineItemId() {
		return lineItemId;
	}
	/**
	 * @param id the id to set
	 */
	public void setLineItemId(Long lineItemId) {
		this.lineItemId = lineItemId;
	}
	/**
	 * @return the itemName
	 */
	public String getItemName() {
		return itemName;
	}
	/**
	 * @param itemName the itemName to set
	 */
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	/**
	 * @return the itemQuantity
	 */
	public String getItemQuantity() {
		return itemQuantity;
	}
	/**
	 * @param itemQuantity the itemQuantity to set
	 */
	public void setItemQuantity(String itemQuantity) {
		this.itemQuantity = itemQuantity;
	}

	

	
}
