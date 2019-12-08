package io.jc.item.errorHandler;

public class ItemNotFoundException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ItemNotFoundException(String exception) {
		super(exception);
	}

	public ItemNotFoundException() {
		// TODO Auto-generated constructor stub
		super();
	}
}
