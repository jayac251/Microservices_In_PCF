package io.jc.sales.errorHandler;

public class SalesOrderException extends Exception {
	/**
	 * Default Serialize
	 */
	private static final long serialVersionUID = 1L;
	
	public SalesOrderException(String string) {
		super(string);
	}

	public SalesOrderException(String string, Exception e) {
		super(string,e);
	}

	
}
