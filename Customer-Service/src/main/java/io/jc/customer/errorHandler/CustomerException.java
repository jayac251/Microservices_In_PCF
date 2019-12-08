package io.jc.customer.errorHandler;

public class CustomerException extends Exception {

	/**
	 * Default Serialize
	 */
	private static final long serialVersionUID = 1L;
	
	public CustomerException(String string) {
		super(string);
	}

	public CustomerException(String string, Exception e) {
		super(string,e);
	}

	

}
