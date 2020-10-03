package za.co.setvin.exception;

public class InvalidOrderException extends RuntimeException {
	
	public InvalidOrderException() {
		super();
	}
	
	public InvalidOrderException(String msg) {
		super(msg);
	}

}
