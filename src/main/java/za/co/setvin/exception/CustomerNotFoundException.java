package za.co.setvin.exception;

import java.util.function.Supplier;

public class CustomerNotFoundException extends RuntimeException {

	public CustomerNotFoundException() {
		super();
	}
	
	public CustomerNotFoundException(String msg) {
		super(msg);
	}

}
