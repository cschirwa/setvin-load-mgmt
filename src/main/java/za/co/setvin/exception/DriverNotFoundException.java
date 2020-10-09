package za.co.setvin.exception;

public class DriverNotFoundException extends RuntimeException{

	public DriverNotFoundException() {
		super();
	}
	
	public DriverNotFoundException(String msg) {
		super(msg);
	}
	
	public DriverNotFoundException(String msg, Throwable e) {
		super(msg, e);
	}
	
	
}
