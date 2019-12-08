package io.jc.item.errorHandler;

public class ItemException extends Exception {

	/**
	 * Default Serialize
	 */
	private static final long serialVersionUID = 1L;
	
	public ItemException(String string) {
		super(string);
	}

	public ItemException(String string, Exception e) {
		super(string,e);
	}

	

}
