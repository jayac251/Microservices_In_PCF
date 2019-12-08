package io.jc.sales.errorHandler;

public class OrderNotFoundException extends Exception {
	private static final long serialVersionUID = 1L;

	public OrderNotFoundException(String exception) {
		super(exception);
	}

	public OrderNotFoundException() {
		// TODO Auto-generated constructor stub
		super();
	}
}
