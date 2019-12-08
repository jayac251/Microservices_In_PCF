/**
 * 
 */
package io.jc.sales.repo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @author 197651
 *
 */

@Entity
public class CustomerSOS {

	@Id
	@GeneratedValue
	Long custId;	
	String custFirstName;
	String custLastName;
	String custEmail;
	
	//Default Constructor
	public CustomerSOS() {
		super();
	}
	
	/**
	 * @param custFirstName
	 * @param custLastName
	 * @param custEmail
	 */
	public CustomerSOS(String custFirstName, String custLastName, String custEmail) {
		super();
		this.custFirstName = custFirstName;
		this.custLastName = custLastName;
		this.custEmail = custEmail;
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
	 * @return the custFirstName
	 */
	public String getCustFirstName() {
		return custFirstName;
	}

	/**
	 * @param custFirstName the custFirstName to set
	 */
	public void setCustFirstName(String custFirstName) {
		this.custFirstName = custFirstName;
	}

	/**
	 * @return the custLastName
	 */
	public String getCustLastName() {
		return custLastName;
	}

	/**
	 * @param custLastName the custLastName to set
	 */
	public void setCustLastName(String custLastName) {
		this.custLastName = custLastName;
	}

	/**
	 * @return the custEmail
	 */
	public String getCustEmail() {
		return custEmail;
	}

	/**
	 * @param custEmail the custEmail to set
	 */
	public void setCustEmail(String custEmail) {
		this.custEmail = custEmail;
	}
	
	
}
