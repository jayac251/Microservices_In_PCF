package io.jc.customer.errorHandler;

public class CustomerNotFoundException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public CustomerNotFoundException(String exception) {
		super(exception);
	}

	public CustomerNotFoundException() {
		// TODO Auto-generated constructor stub
		super();
	}
}
