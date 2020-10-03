package za.co.setvin.exception;

public class InvalidCustomerException extends RuntimeException {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public InvalidCustomerException() {
		super();
	}
	
	public InvalidCustomerException(String msg) {
		super(msg);
	}
	
	public InvalidCustomerException(String msg, Throwable e) {
		super(msg, e);
	}

}
